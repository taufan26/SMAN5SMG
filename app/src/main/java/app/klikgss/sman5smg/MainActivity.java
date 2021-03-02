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
    userManagement userManagement;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //inisialisasi
        showFoto = findViewById(R.id.fotoprofil);
        Nama = findViewById(R.id.NamaSiswa);
        Username = findViewById(R.id.UsernameSiswa);

//        editor = sharedPreferences.edit();



//        userManagement = new userManagement(this);
//        userManagement.checkLogin();;

//        Intent i = getIntent();
//        String mNama = i.getStringExtra("nama");
//        String mUsername = i.getStringExtra("username");
//        Nama.setText(mNama);
//        Username.setText(mUsername);


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

                userManagement.logout();

            }
        });

    }



}