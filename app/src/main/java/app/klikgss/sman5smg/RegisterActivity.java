package app.klikgss.sman5smg;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class RegisterActivity extends AppCompatActivity {

    EditText editTextfullname, editTextusername, editTextpass, editTextemail;
    CardView cardViewregister;
    TextView login_msg;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        login_msg = findViewById(R.id.textView2);
        editTextusername = findViewById(R.id.RegisUsername);
        editTextfullname = findViewById(R.id.RegisFullname);
        editTextpass = findViewById(R.id.Regispasslagi);
        editTextemail = findViewById(R.id.Regisemail);
        cardViewregister = findViewById(R.id.registercard);
        progressBar = findViewById(R.id.RegisProgress);

        cardViewregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullname, username, password, email;
                fullname = String.valueOf(editTextfullname.getText());
                username = String.valueOf(editTextusername.getText());
                password = String.valueOf(editTextpass.getText());
                email = String.valueOf(editTextemail.getText());

                if (!fullname.equals("") && !username.equals("") && !password.equals("") && !email.equals("")) {
                    progressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[4];
                            field[0] = "fullname";
                            field[1] = "username";
                            field[2] = "password";
                            field[3] = "email";
                            String[] data = new String[4];
                            data[0] = fullname;
                            data[1] = username;
                            data[2] = password;
                            data[3] = email;
                            PutData putData = new PutData("http://192.168.18.130/LoginRegister/Signup.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    if (result.equals("Sign Up Success")){
                                        Toast.makeText(getApplicationContext(), result,Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(), result,Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(getApplicationContext(),"Semua Field Harus Di Isi", Toast.LENGTH_SHORT).show();
                }
            }
        });


        login_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(v.getContext(), LoginActivity.class);
                startActivity(login);
            }
        });

    }
}

