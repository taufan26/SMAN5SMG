package app.klikgss.sman5smg;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import app.klikgss.sman5smg.api.ApiClient;
import app.klikgss.sman5smg.api.ApiInterface;
import app.klikgss.sman5smg.model.login.Data;
import app.klikgss.sman5smg.model.login.Login;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private CheckBox ShowPass;
    EditText  usernameLogin, passwordLogin;
    TextView btnlogin, textViewlupapassword;
    String Username, Password;
    ProgressDialog progressDialog;
    ApiInterface apiInterface;
    SessionManger sessionManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        // inisialisasi login
        usernameLogin = findViewById(R.id.loginusername);
        passwordLogin= findViewById(R.id.loginpass);

        btnlogin = findViewById(R.id.login_btn);
        btnlogin.setOnClickListener(this);

        //lupa password
        textViewlupapassword = findViewById(R.id.loginforget);
        textViewlupapassword.setOnClickListener(this);

        //viewpass
        ShowPass = findViewById(R.id.showPass);
        ShowPass.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("loading....");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.login_btn:
                progressDialog.show();
                Username = usernameLogin.getText().toString();
                Password = passwordLogin.getText().toString();
                login(Username,Password);
                break;
            case R.id.showPass:
                showpass();
                break;
            case R.id.loginforget:
                Intent intent = new Intent(this, ForgetPasswordActivity.class);
                startActivity(intent);
        }
    }

    private void showpass() {
        if(ShowPass.isChecked()){
            //Saat Checkbox dalam keadaan Checked, maka password akan di tampilkan
            passwordLogin.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }else {
            //Jika tidak, maka password akan di sembuyikan
            passwordLogin.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }

    private void login(String username, String password) {

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Login> loginCall = apiInterface.loginResponse(username, password);
        loginCall.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if (response.body() != null && response.isSuccessful() && response.body().isStatus()){
                    progressDialog.dismiss();

                    sessionManger = new SessionManger(LoginActivity.this);
                    Data data = response.body().getData();
                    sessionManger.createLoginSession(data);

                    Toast.makeText(LoginActivity.this, response.body().getData().getNama(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
