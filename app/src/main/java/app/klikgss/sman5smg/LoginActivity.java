package app.klikgss.sman5smg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView regis_msg = findViewById(R.id.textView2);

       regis_msg.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent regis = new Intent(v.getContext(), RegisterActivity.class);
               startActivityForResult(regis, 0);
           }
       });
}

}
