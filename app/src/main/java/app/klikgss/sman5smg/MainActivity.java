package app.klikgss.sman5smg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    TextView getnamasiswa, getkelas, getbingung;
    ImageView getfotoprofil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getfotoprofil= findViewById(R.id.fotoprofil);
        getnamasiswa = findViewById(R.id.NamaSiswa);
        getkelas = findViewById(R.id.Kelas);
        getbingung = findViewById(R.id.bingung);



    }

}