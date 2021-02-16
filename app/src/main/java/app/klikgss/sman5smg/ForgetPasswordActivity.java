package app.klikgss.sman5smg;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.android.volley.Request.Method.POST;

public class ForgetPasswordActivity extends AppCompatActivity {

    EditText editTextemail;
    TextView btnforgot;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pasword);

        editTextemail = (EditText)findViewById(R.id.email);
        btnforgot = (TextView)findViewById(R.id.forget_btn);
        progressBar = findViewById(R.id.forgetProgress);
        submith();
    }

    private void submith() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading . . .");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        btnforgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                String email = editTextemail.getText().toString().trim();
                if (email.isEmpty()){
                    progressDialog.dismiss();
                    massage("Enter email valid!");
                }else{
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.Forgot_URL,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject object = new JSONObject(response);
                                        String mail = object.getString("mail");
                                        if (mail.equals("Send")){
                                            progressDialog.dismiss();
                                            massage("Email are Successfully send");
                                        }else {
                                            progressDialog.dismiss();
                                            massage(response);
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss();
                            massage(error.toString());
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String, String> forgotparams = new HashMap<>();
                            forgotparams.put("email",email);
                            return forgotparams;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(ForgetPasswordActivity.this);
                    requestQueue.add(stringRequest);
                }
            }
        });
    }

    private void massage(String massage) {
        Toast.makeText(this, massage,Toast.LENGTH_LONG).show();
    }

}
