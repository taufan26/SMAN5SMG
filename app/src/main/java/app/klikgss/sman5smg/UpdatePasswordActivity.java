package app.klikgss.sman5smg;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdatePasswordActivity extends AppCompatActivity {

    EditText edpass, edpassag;
    TextView btnpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);

        edpass = findViewById(R.id.password);
        edpassag = findViewById(R.id.password_again);
        btnpass = findViewById(R.id.pass_btn);

        Uri uri = getIntent().getData();
        if (uri != null){
            List<String> params = uri.getPathSegments();
            String username = params.get(params.size()- 1);
            Toast.makeText(this, "username="+username, Toast.LENGTH_SHORT).show();

            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Loading . . .");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

            btnpass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String password = edpass.getText().toString().trim();
                    String cpassword = edpassag.getText().toString().trim();

                    if (password.isEmpty() || cpassword.isEmpty()){
                        Toast.makeText(UpdatePasswordActivity.this, "Harap Isi Semua field", Toast.LENGTH_SHORT).show();
                    }else {
                        progressDialog.show();
                        StringRequest request = new StringRequest(Request.Method.POST, Urls.update_url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        progressDialog.dismiss();

                                        if (response.equals("Password Berhasil di ganti!")){
                                            Toast.makeText(UpdatePasswordActivity.this, response, Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                            finish();
                                        }else {
                                            Toast.makeText(UpdatePasswordActivity.this, response, Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(UpdatePasswordActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        }){
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                HashMap<String, String> updateparams = new HashMap<>();
                                updateparams.put("password",password);
                                updateparams.put("cpassword",cpassword);
                                updateparams.put("username",username);
                                return updateparams;
                            }
                        };
                        RequestQueue requestQueue = Volley.newRequestQueue(UpdatePasswordActivity.this);
                        requestQueue.add(request);
                    }
                }
            });
        }
    }
}
