package app.klikgss.sman5smg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.time.Clock;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    ImageView showFoto;
    TextView Nama, Username;
    SessionManger sessionManger;
    String username, nama;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //session
        sessionManger = new SessionManger(MainActivity.this);
        if (sessionManger.isLoggedIn() == false){
            moveToLogin();
        }

        //inisialisasi
        showFoto = findViewById(R.id.fotoprofil);
        Nama = findViewById(R.id.NamaSiswa);
        Username = findViewById(R.id.UsernameSiswa);

        username = sessionManger.getUserDetail().get(SessionManger.USERNAME);
        nama = sessionManger.getUserDetail().get(SessionManger.NAMA);

        Username.setText(username);
        Nama.setText(nama);

        // show calendar
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        TextView textViewDate = findViewById(R.id.text_view_tanggal);
        textViewDate.setText(currentDate);

        CardView cardView = findViewById(R.id.menu1); // intent cardview
        CardView cv_luring = findViewById(R.id.menu2);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PresensiActivity.class);
                startActivity(intent);
            }
        });


        cv_luring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainLuringActivity.class);
                startActivity(i);
            }
        });

        CardView cv_logout = findViewById(R.id.logout);
        cv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManger.logoutSession();
                moveToLogin();
            }
        });
    }

    private void moveToLogin() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }


}