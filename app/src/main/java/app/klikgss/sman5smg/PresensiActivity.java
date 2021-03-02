package app.klikgss.sman5smg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.os.Bundle;
import java.util.Locale;

import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import com.android.volley.Request;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PresensiActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;

    EditText InptDate;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private Object GoogleMap;
    private Button btn_submit;
    private Spinner sp_kelas, sp_status;
    private EditText btDatePicker;
    private TextView tvDateResult;
    Context mContext;
    private static final int REQUEST_CODE_LOCATION_PERMISSIONS = 1;
    private TextView textLatLong, textAdress;
    private ProgressBar progressBar;
    private ResultReceiver resultReceiver;


    private void showDateDialog() {
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker View, int year, int monthOfyear, int dayOfmonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfyear, dayOfmonth);
                btDatePicker.setText(dateFormatter.format(newDate.getTime()));
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presensi);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        btDatePicker = findViewById(R.id.et_tanggal);
        tvDateResult = findViewById(R.id.et_tanggal);
        sp_status = findViewById(R.id.spinnerStatus);
        sp_kelas = findViewById(R.id.spinnerKelas);
        btn_submit = (Button) findViewById(R.id.SaveAbsen);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Kehadiran, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_status.setAdapter(adapter);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng crumlin = new LatLng(53.3252584, -6.3089222);
        googleMap.addMarker(new MarkerOptions()
                .position(crumlin)
                .title("CRUMLLIN"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(crumlin));

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        tvDateResult = (TextView) findViewById(R.id.et_tanggal);
        btDatePicker = (EditText) findViewById(R.id.et_tanggal);
        btDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });

        resultReceiver = new AddressResultRecevier(new Handler());
        textLatLong = findViewById(R.id.textLatLong);
        progressBar = findViewById(R.id.progressBarMap);
        textAdress = findViewById(R.id.textAdress);


        findViewById(R.id.btn_Lokasi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(
                        getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            PresensiActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            REQUEST_CODE_LOCATION_PERMISSIONS
                    );

                } else {
                    getCurrentLocation();
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_LOCATION_PERMISSIONS && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();

            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getCurrentLocation() {
        progressBar.setVisibility(View.VISIBLE);
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.getFusedLocationProviderClient(PresensiActivity.this)
                .requestLocationUpdates(locationRequest, new LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                        LocationServices.getFusedLocationProviderClient(PresensiActivity.this)
                               .removeLocationUpdates(this);
                        if(locationRequest != null && locationResult.getLocations().size() > 0){
                          int latestLocationIndex = locationResult.getLocations().size() - 1;
                          double latitude =
                                  locationResult.getLocations().get(latestLocationIndex).getLatitude();
                          double longitude =
                                  locationResult.getLocations().get(latestLocationIndex).getLongitude();
                          textLatLong.setText(
                                  String.format(
                                          "Latitude: %s\nLongitude: %s",
                                          latitude,
                                          longitude
                                  )
                          );

                          Location location = new Location("providerNA");
                          location.setLatitude(latitude);
                          location.setLongitude(longitude);
                          fetchAddressFromLatLong(location);

                        }else {
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                }, Looper.getMainLooper());


    }
    private  void fetchAddressFromLatLong(Location location){
        Intent intent = new Intent(this,FetchIntentAddressService.class);
        intent.putExtra(Constants.RECEIVER, resultReceiver);
        intent.putExtra(Constants.LOCATION_DATA_EXTRA, location);
        startService(intent);
    }

    private class AddressResultRecevier extends ResultReceiver{

        public AddressResultRecevier(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);
            if(resultCode == Constants.SUCCESS_RESULT){
                textAdress.setText(resultData.getString(Constants.RESULT_DATA_KEY));
            }else {
                Toast.makeText(PresensiActivity.this, resultData.getString(Constants.RESULT_DATA_KEY), Toast.LENGTH_SHORT).show();

            }
            progressBar.setVisibility(View.GONE);
        }
    }

}