package com.t.and.tt.Activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.orhanobut.hawk.Hawk;
import com.t.and.tt.API.API;
import com.t.and.tt.API.APIClient;

import com.t.and.tt.Model.GoogleLogIn.ContentGoogle;
import com.t.and.tt.Model.GoogleLogIn.GoogleLogInModel;
import com.t.and.tt.Model.LogIn.ContentLogInModel;
import com.t.and.tt.Model.LogIn.LogInModel;
import com.t.and.tt.Model.SearchBusiness.Content;
import com.t.and.tt.Other.NetworkChangeReceiver;
import com.t.and.tt.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.t.and.tt.Util.util.rotate;

public class LogInActivity extends AppCompatActivity {
    Button btnSignIn,btnGoogelSignUp;
    TextView txtForgotPwd;
    API apiInterface;
    FrameLayout framLoading;
    ImageView imgLoader;
    int RC_SIGN_IN = 100;
    EditText edtEmail, edtPwd;
    LinearLayout linearSignUp;
    GoogleSignInClient mGoogleSignInClient;
    String googleName, googleEmail, googleToken, googleID, URL_GOOGLE;
    private NetworkChangeReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        receiver = new NetworkChangeReceiver();
        registerReceiver(receiver, filter);
        apiInterface = APIClient.getClient().create(API.class);

        imgLoader       = findViewById(R.id.img_loader);
        framLoading     = findViewById(R.id.len_loding);
        framLoading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        rotate(getApplicationContext(),imgLoader,false,framLoading);

        edtPwd =findViewById(R.id.edt_Pwd);
        edtEmail =  findViewById(R.id.edt_Email);
        btnSignIn=findViewById(R.id.btn_SignIn);
        btnGoogelSignUp=findViewById(R.id.btnGoogelSignUp);
        linearSignUp=findViewById(R.id.linearSignUp);



        linearSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LogInActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                submitForm();

            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.google_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        btnGoogelSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googleSignIn();
            }
        });

    }
    private void submitForm() {
        if (!validateEmail()) {
            return;
        }


        if (!validatePassword()) {
            return;
        }
        SingIn();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private boolean validateEmail() {
        String email = edtEmail.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            edtEmail.setError(getString(R.string.validEmail));
            requestFocus(edtEmail);
            return false;
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean validatePassword() {
        if (edtPwd.getText().toString().trim().isEmpty()) {
            edtPwd.setError(getString(R.string.validPassword));
            requestFocus(edtPwd);
            return false;
        }

        return true;
    }

    private void googleSignIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        } else {
//            callbackManager.onActivityResult(requestCode, resultCode, data);
//            super.onActivityResult(requestCode, resultCode, data);
        }

    }


    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);


            if (account != null) {
                googleName = account.getDisplayName();
                String personGivenName = account.getGivenName();
                String personFamilyName = account.getFamilyName();
                googleEmail = account.getEmail();
                googleID = account.getId();
                googleToken = account.getIdToken();
                Uri personPhoto = account.getPhotoUrl();
                Log.v("googleToken", googleToken);

                if (googleID != null && !googleID.isEmpty() && !googleID.equals("null")) {
                    googleLogIn();
                }


//
            }

            // Signed in successfully, show authenticated UI.
            // updateUI(account);
        } catch (ApiException e) {


            String xyz = e.getMessage().toString();
            String j = xyz;
            Log.v("xyz", xyz);

        }
    }
    public void SingIn() {
        rotate(getApplicationContext(),imgLoader,true,framLoading);
        String email=edtEmail.getText().toString().trim();

        Call<LogInModel> call = apiInterface.Login(email, edtPwd.getText().toString().trim(),"Android","xyz");
        call.enqueue(new Callback<LogInModel>() {
            @Override
            public void onResponse(Call<LogInModel> call, Response<LogInModel> response) {

                LogInModel logInModel = new LogInModel();
                Gson gson = new Gson();
                JsonElement jsonElement = gson.toJsonTree(response.body());
                logInModel = gson.fromJson(jsonElement, LogInModel.class);
                Log.e("resultLogIn",jsonElement.toString());
                ContentLogInModel contentLogInModel = logInModel.getContent();
                String status=logInModel.getStatus();
                if (status.equals("success")) {
                    String userToken="Bearer "+contentLogInModel.getAccessToken();
                    String  user_id=""+contentLogInModel.getUserId();
                    Hawk.put("logedIn", 1);
                    Hawk.put("userToken",userToken);
                    Hawk.put("user_id",user_id);
                    Hawk.put("logInModel",logInModel);
                    Hawk.put("logInMethod","normal");
                    Log.v("userTokenLogIn",userToken);
                    Intent home = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(home);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.Failed), Toast.LENGTH_SHORT).show();
                }
                rotate(getApplicationContext(),imgLoader,false,framLoading);

            }

            @Override
            public void onFailure(Call<LogInModel> call, Throwable t) {
                Log.e("BagResponse", call.toString());
                rotate(getApplicationContext(),imgLoader,false,framLoading);
            }
        });
    }

    public void googleLogIn() {
        rotate(getApplicationContext(),imgLoader,true,framLoading);
        Call<GoogleLogInModel> call = apiInterface.googleLogIN(googleName, googleEmail,"0","Android","xyz",googleID);
        call.enqueue(new Callback<GoogleLogInModel>() {
            @Override
            public void onResponse(Call<GoogleLogInModel> call, Response<GoogleLogInModel> response) {

                GoogleLogInModel googleLogInModel = new GoogleLogInModel();
                Gson gson = new Gson();
                JsonElement jsonElement = gson.toJsonTree(response.body());
                googleLogInModel = gson.fromJson(jsonElement, GoogleLogInModel.class);
                Log.e("resultLogIn",jsonElement.toString());
                ContentGoogle contentGoogle = googleLogInModel.getContent();
                String status=googleLogInModel.getStatus();
                if (status.equals("success")) {
                    String userToken="Bearer "+contentGoogle.getAccessToken();
                    String  user_id=""+contentGoogle.getUserId();
                    Hawk.put("logedIn", 1);
                    Hawk.put("userToken",userToken);
                    Hawk.put("user_id",user_id);
                    Hawk.put("logInMethod","google");
                    Log.v("userTokenLogIn",userToken);
                    Hawk.put("googleLogInModel",googleLogInModel);
                    Intent home = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(home);
                    finish();

                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.Failed), Toast.LENGTH_SHORT).show();
                }
                rotate(getApplicationContext(),imgLoader,false,framLoading);

            }

            @Override
            public void onFailure(Call<GoogleLogInModel> call, Throwable t) {
                Log.e("BagResponse", call.toString());
                rotate(getApplicationContext(),imgLoader,false,framLoading);
            }
        });
    }
}
