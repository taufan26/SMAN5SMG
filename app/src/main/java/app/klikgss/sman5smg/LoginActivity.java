package app.klikgss.sman5smg;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private CheckBox ShowPass;
    EditText  usernameLogin, passwordLogin;
    EditText usernameRegister, passwordRegister, namaRegister;
    TextView cv_Login, textViewlupapassword,tv_register;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        // inisialisasi login
        usernameLogin = findViewById(R.id.loginusername);
        passwordLogin= findViewById(R.id.loginpass);
        cv_Login = findViewById(R.id.login_btn);


        //inisialisasi register
        usernameRegister = findViewById(R.id.Inptusername);
        passwordRegister = findViewById(R.id.Inptpassword);
        namaRegister = findViewById(R.id.InptNama);
        //tv_register = findViewById(R.id.register_btn);


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("loading....");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        //lupa password
        textViewlupapassword = findViewById(R.id.loginforget);
        ShowPass = findViewById(R.id.showPass);

        ShowPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ShowPass.isChecked()){
                    //Saat Checkbox dalam keadaan Checked, maka password akan di tampilkan
                    passwordLogin.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    //Jika tidak, maka password akan di sembuyikan
                    passwordLogin.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

//        tv_register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                 UserRegristationProccess();
//
//            }
//        });

        cv_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserLoginProccess();
            }
        });

        textViewlupapassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ForgetPasswordActivity.class));
                finish();
            }
        });

    }

            private void UserLoginProccess() {



                final String usernamelogin = usernameLogin.getText().toString().trim();
                final String passwordlogin = passwordLogin.getText().toString().trim();

                if (usernamelogin.isEmpty() || passwordlogin.isEmpty()){
                    message("Some fields are empty");
                }else{
                        progressDialog.show();
                        StringRequest request = new StringRequest(Request.Method.POST, Urls.Login_URL,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(response);
                                            String result = jsonObject.getString("status");
//                                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                                            if(result.equals("success")) {
//                                                progressDialog.dismiss();

//                                                for (int i = 0; i < jsonArray.length(); i++) {
//                                                    JSONObject object = jsonArray.getJSONObject(i);
//                                                     String nama = object.getString("nama");
//                                                     String username = object.getString("username");


                                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                                                    intent.putExtra("nama", nama);
//                                                    intent.putExtra("username", username);
                                                    startActivity(intent);finish();

                                                    message("Login Berhasil");
//                                                }

                                            }else{
                                                progressDialog.dismiss();
                                                message("Login Gagal, Periksa kembali username atau password anda");
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }


                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progressDialog.dismiss();
                                message(error.getMessage());

                            }
                        }){
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String,String> params = new HashMap<>();

                                params.put("username",usernamelogin);
                                params.put("password",passwordlogin);
                                return params;
                            }
                        };
                        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                        queue.add(request);

                }
            }


//            private void UserRegristationProccess() {
//                LayoutInflater inflater = getLayoutInflater();
//                View activity_register = inflater.inflate(R.layout.activity_register,null);
//                EditText Username  = activity_register.findViewById(R.id.Inptusername);
//                EditText Password = activity_register.findViewById(R.id.Inptpassword);
//                EditText Nama = activity_register.findViewById(R.id.Inptusername);
//                AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                builder.setView(activity_register);
//                builder.setTitle("Registation");
//                builder.setPositiveButton("Register", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                        progressDialog.show();
//                        final String username = Username.getText().toString().trim();
//                        final String password = Password.getText().toString().trim();
//                        final String nama = Nama.getText().toString().trim();
//
//                        if (username.isEmpty() || password.isEmpty() || nama.isEmpty()) {
//                            message("Some Fields are empty..");
//                            progressDialog.dismiss();
//                        }else {
//
//                            StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.Register_URL,
//
//                                    new Response.Listener<String>() {
//                                        @Override
//                                        public void onResponse(String response) {
//                                            progressDialog.dismiss();
//                                            message(response);
//
//                                        }
//                                    }, new Response.ErrorListener() {
//                                @Override
//                                public void onErrorResponse(VolleyError error) {
//                                    progressDialog.dismiss();
//                                    message(error.getMessage());
//
//                                }
//                            }) {
//                                @Override
//                                protected Map<String, String> getParams() throws AuthFailureError {
//
//                                    Map<String, String> params = new HashMap<>();
//                                    params.put("username", username);
//                                    params.put("password", password);
//                                    params.put("nama", nama);
//                                    return params;
//                                }
//
//                            };
//                            RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
//                            queue.add(stringRequest);
//                        }
//                    }
//                });
//                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                        dialogInterface.dismiss();
//
//                    }
//                });
//
//                AlertDialog dialog = builder.create();
//                dialog.show();
//
//
//    }
    public void message (String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }

}
