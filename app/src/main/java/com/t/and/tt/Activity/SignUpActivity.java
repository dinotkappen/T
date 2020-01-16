package com.t.and.tt.Activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
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

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.orhanobut.hawk.Hawk;
import com.t.and.tt.API.API;
import com.t.and.tt.API.APIClient;
import com.t.and.tt.Model.SignUp.SignUpModel;
import com.t.and.tt.Other.NetworkChangeReceiver;
import com.t.and.tt.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.t.and.tt.Util.util.rotate;

public class SignUpActivity extends AppCompatActivity {

    Button btnSignup;
    TextView txtSkip;
    API apiInterface;
    FrameLayout framLoading;
    ImageView imgLoader;
    LinearLayout linearSignIn;
    private NetworkChangeReceiver receiver;
    EditText edtName, edtPhone, edtPwd, edtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        receiver = new NetworkChangeReceiver();
        registerReceiver(receiver, filter);

        txtSkip= findViewById(R.id.txtSkip);
        edtPwd = findViewById(R.id.edtPwd);
        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPhone = findViewById(R.id.edtPhone);
        btnSignup = findViewById(R.id.btnSignup);
        linearSignIn = findViewById(R.id.linearSignIn);
        apiInterface = APIClient.getClient().create(API.class);

        imgLoader       = findViewById(R.id.img_loader);
        framLoading     = findViewById(R.id.len_loding);
        framLoading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        rotate(getApplicationContext(),imgLoader,false,framLoading);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                submitForm();
            }
        });

        linearSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignUpActivity.this,LogInActivity.class);
                startActivity(intent);

            }
        });

    }

    private void submitForm() {

        if (!validateName()) {
            return;
        }
        if (!validatePhone()) {
            return;
        }

        if (!validateEmail()) {
            return;
        }

        if (!validatePassword()) {
            return;
        }


        SingUp();
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private boolean validateName() {
        if (edtName.getText().toString().trim().isEmpty()) {
            edtName.setError(getString(R.string.validName));
            requestFocus(edtName);
            return false;
        }

        return true;
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

    private boolean validatePhone() {
        if (edtPhone.getText().toString().trim().isEmpty()) {
            edtPhone.setError(getString(R.string.validPhone));
            requestFocus(edtPhone);
            return false;
        }
        int length=edtPhone.getText().toString().length();
        if ( length< 8) {
            edtPhone.setError(getString(R.string.validPhone));
            requestFocus(edtPhone);
            return false;
        }
        return true;
    }

    private boolean validatePassword() {
        if (edtPwd.getText().toString().trim().isEmpty()) {
            edtPwd.setError(getString(R.string.validPassword));
            requestFocus(edtPwd);
            return false;
        }

        if (TextUtils.isEmpty(edtPwd.getText().toString()) || edtPwd.getText().toString().length() < 8) {
            edtPwd.setError(getString(R.string.validPwdLength));
            requestFocus(edtPwd);
            return false;
        }
        return true;
    }

    public void SingUp(){
        rotate(getApplicationContext(),imgLoader,true,framLoading);

        Call<SignUpModel> call = apiInterface.SignUp(edtName.getText().toString().trim(),edtEmail.getText().toString().trim(),edtPwd.getText().toString().trim(),edtPhone.getText().toString().trim(),"Android","xyz");
        call.enqueue(new Callback<SignUpModel>() {
            @Override
            public void onResponse(Call<SignUpModel> call, Response<SignUpModel> response) {
                Log.e("response",response.toString());

                SignUpModel signUpModel =new SignUpModel();
                Gson gson = new Gson();
                JsonElement jsonElement = gson.toJsonTree(response.body());
                signUpModel = gson.fromJson(jsonElement, SignUpModel.class);
                Log.e("resultSigNUp",jsonElement.toString());
                if (signUpModel.getStatus().equals("success")){
                    String message=signUpModel.getMessage();
                    Log.v("signUpMSG",message);
                    Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
                    finish();
                }
                else
                {
                    String message=signUpModel.getMessage();
                    Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
                    Log.v("signUpMSG",message);
                    finish();
                }
                rotate(getApplicationContext(),imgLoader,false,framLoading);
            }

            @Override
            public void onFailure(Call<SignUpModel> call, Throwable t) {
                rotate(getApplicationContext(),imgLoader,false,framLoading);
            }
        });
    }
}
