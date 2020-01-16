package com.t.and.tt.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.orhanobut.hawk.Hawk;
import com.t.and.tt.API.API;
import com.t.and.tt.API.APIClient;
import com.t.and.tt.Activity.LogInActivity;
import com.t.and.tt.Adapter.ViewPagerAdapter_banner;
import com.t.and.tt.Application;
import com.t.and.tt.Model.GoogleLogIn.GoogleLogInModel;
import com.t.and.tt.Model.LogIn.LogInModel;
import com.t.and.tt.Model.Profile.ProfileModel;
import com.t.and.tt.Model.Profile.ProifleContent;
import com.t.and.tt.Other.CompressImage;
import com.t.and.tt.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;
import static com.t.and.tt.Activity.MainActivity.toggle;
import static com.t.and.tt.Util.util.rotate;


public class Profile extends Fragment {


    LogInModel logInModel = new LogInModel();
    GoogleLogInModel googleLogInModel = new GoogleLogInModel();
    EditText edt_Name, edt_Phone, edt_Email;
    Button btn_Update;
    String logInMethod;
    String name,email,phone;
    ImageView profile_image;

    private SharedPreferences permissionStatus;
    private boolean sentToSettings = false;
    private static final int PERMISSION_CALLBACK_CONSTANT = 100;
    private static final int REQUEST_PERMISSION_SETTING = 101;
    String[] permissionsRequired = new String[]{Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private static final int MY_CAMERA_REQUEST_CODE = 100;
    private static final int MY_GALLERY_REQUEST_CODE = 101;
    ArrayList<Uri> img_List = new ArrayList<>();
    Bitmap photo = null;
    Uri selectedMediaUri;
    String userToken,user_id;
    FrameLayout framLoading;
    ImageView imgLoader;
    File ImagePath=null;
    API apiInterface;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        apiInterface = APIClient.getClient().create(API.class);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.activity_profile, container, false);
        edt_Name = rootView.findViewById(R.id.edt_Name);
        edt_Phone = rootView.findViewById(R.id.edt_Phone);
        edt_Email = rootView.findViewById(R.id.edt_Email);
        btn_Update = rootView.findViewById(R.id.btn_Update);
        profile_image = rootView.findViewById(R.id.profile_image);
        apiInterface = APIClient.getClient().create(API.class);

        imgLoader       = rootView.findViewById(R.id.img_loader);
        framLoading     = rootView.findViewById(R.id.len_loding);
        framLoading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        rotate(getActivity(),imgLoader,false,framLoading);



        userToken=Hawk.get("userToken",userToken);
        Log.v("userToken",userToken);
        logInMethod=Hawk.get("logInMethod",logInMethod);
        user_id=Hawk.get("user_id",user_id);
        Log.v("user_id",user_id);
        if(logInMethod.equals("normal"))
        {
            logInModel = Hawk.get("logInModel", logInModel);
            name = logInModel.getContent().getName();
            phone = logInModel.getContent().getPhone();
            email = logInModel.getContent().getEmail();
        }
        else
        {
            googleLogInModel= Hawk.get("googleLogInModel", googleLogInModel);
            name = googleLogInModel.getContent().getName();
            phone = googleLogInModel.getContent().getPhone();
            if (phone != null && !phone.isEmpty() && !phone.equals("null")) {
                {
                    if(phone.equals("0"))
                    {
                        phone="";
                    }
                }
            }

            email = googleLogInModel.getContent().getEmail();
        }




        if (name != null && !name.isEmpty() && !name.equals("null")) {
            edt_Name.setText(name);
        }
        if (phone != null && !phone.isEmpty() && !phone.equals("null")) {
            edt_Phone.setText(phone);
        }

        if (email != null && !email.isEmpty() && !email.equals("null")) {
            edt_Email.setText(email);
        }

        permissionStatus = getActivity().getSharedPreferences("permissionStatus", MODE_PRIVATE);
        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        btn_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
            }
        });
        return rootView;

    }
    public void updateProfile(){
        rotate(getActivity(),imgLoader,true,framLoading);
        MultipartBody.Part user_image=null;

        if (ImagePath != null) {
            // empty or doesn't exist
            user_image = APIClient.getRequest("photo",ImagePath);
        }
        RequestBody name= APIClient.plain(edt_Name.getText().toString());
        RequestBody phone= APIClient.plain(edt_Phone.getText().toString());
        RequestBody userid= APIClient.plain(user_id);


        Call<ProfileModel> call = apiInterface.updateProfile(name,userid,phone,user_image,userToken);
        call.enqueue(new Callback<ProfileModel>() {
            @Override
            public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {

                ProfileModel details = new ProfileModel();
                Gson gson = new Gson();
                JsonElement jsonElement = gson.toJsonTree(response.body());
                Log.e("response",jsonElement.toString());
                details = gson.fromJson(jsonElement, ProfileModel.class);
                Hawk.put("detailsProfile",details);

                if (details.getStatus().equals("success")){
                    Toast.makeText(Application.getContext1(),details.getStatus(),Toast.LENGTH_SHORT).show();
                    ProifleContent userDt = new ProifleContent();
                    Log.v("fg","fg");


                }
                else {
                    Toast.makeText(Application.getContext1(),details.getStatus(),Toast.LENGTH_SHORT).show();
                }
                rotate(getActivity(),imgLoader,false,framLoading);
            }

            @Override
            public void onFailure(Call<ProfileModel> call, Throwable t) {
                rotate(getActivity(),imgLoader,false,framLoading);
            }
        });
    }
    private void selectImage() {
        try {
            if (ActivityCompat.checkSelfPermission(getActivity(), permissionsRequired[0]) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(getActivity(), permissionsRequired[1]) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(getActivity(), permissionsRequired[2]) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), permissionsRequired[0])
                        || ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), permissionsRequired[1])
                        || ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), permissionsRequired[2])) {
                    //Show Information about why you need the permission
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Need Multiple Permissions");
                    builder.setMessage("This app needs Camera and Gallery permissions.");
                    builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            ActivityCompat.requestPermissions(getActivity(), permissionsRequired, PERMISSION_CALLBACK_CONSTANT);
                        }
                    });
                    builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                } else if (permissionStatus.getBoolean(permissionsRequired[0], false)) {
                    //Previously Permission Request was cancelled with 'Dont Ask Again',
                    // Redirect to Settings after showing Information about why you need the permission
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Need Multiple Permissions");
                    builder.setMessage("This app needs Camera and Gallery permissions.");
                    builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            sentToSettings = true;
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
                            intent.setData(uri);
                            startActivityForResult(intent, REQUEST_PERMISSION_SETTING);
                            Toast.makeText(getContext(), "Go to Permissions to Grant  Camera and Gallery", Toast.LENGTH_LONG).show();
                        }
                    });
                    builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                } else {
                    //just request the permission
                    ActivityCompat.requestPermissions(getActivity(), permissionsRequired, PERMISSION_CALLBACK_CONSTANT);
                }


                SharedPreferences.Editor editor = permissionStatus.edit();
                editor.putBoolean(permissionsRequired[0], true);
                editor.commit();
            } else {
                //You already have the permission, just go ahead.
                proceedAfterPermission();
            }
        } catch (Exception ex) {
            Log.v("cv", ex.getMessage().toString());
        }
    }

    private void proceedAfterPermission() {

        // Toast.makeText(getContext(), "We got All Permissions", Toast.LENGTH_LONG).show();
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                //  boolean result=Utility.checkPermission(getActivity());
                if (items[item].equals("Take Photo")) {

                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, MY_CAMERA_REQUEST_CODE);

                } else if (items[item].equals("Choose from Library")) {

                    final Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                    galleryIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                    galleryIntent.setType("image/*");
                    galleryIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                    galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(galleryIntent, "Select Picture"), MY_GALLERY_REQUEST_CODE);
                    // startActivityForResult(galleryIntent, MY_GALLERY_REQUEST_CODE);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent result) {
        super.onActivityResult(requestCode, resultCode, result);
//        createTempFile();
        if (resultCode == RESULT_OK) {
            if (requestCode == MY_CAMERA_REQUEST_CODE) {

                photo = (Bitmap) result.getExtras().get("data");
                Uri tempUri = getImageUri(getContext(), photo);
                File finalFile = new File(getRealPathFromURI(tempUri));
                Glide.with(getContext()).load(finalFile.getAbsolutePath()).into(profile_image);
                String path = CompressImage.compressImage(getContext(), finalFile.getAbsolutePath());
                ImagePath = new File(path);


                // CALL THIS METHOD TO GET THE ACTUAL PATH

            }

            else if (requestCode == MY_GALLERY_REQUEST_CODE){
//            selectedMediaUri = result.getData();
//            photo = (Bitmap) result.getExtras().get("data");

                selectedMediaUri = result.getData();
//            img_List.add(selectedMediaUri);

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedMediaUri);
                    Uri tempUri = getImageUri(getContext(), bitmap);
                    File finalFile = new File(getRealPathFromURI(tempUri));
                    String path = CompressImage.compressImage(getContext(), finalFile.getAbsolutePath());
                    Log.e("file_path",path);
                    ImagePath = new File(path);
                    Glide.with(getContext()).load(finalFile.getAbsolutePath()).into(profile_image);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }




}
