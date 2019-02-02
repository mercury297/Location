package com.example.android.locationdetection;

import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends AppCompatActivity {
    private FusedLocationProviderClient client;
    private TextView getLoc;
    private Button btn;
    String lat = "";
    String longi="";
    String msg="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermission();


        client = LocationServices.getFusedLocationProviderClient(this);
        btn = findViewById(R.id.getLocation);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(MainActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED  ) {

                    return;
                }
                client.getLastLocation().addOnSuccessListener(MainActivity.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if(location!=null){
                            getLoc=findViewById(R.id.location);
                            lat=String.valueOf(location.getLatitude());
                            longi=String.valueOf(location.getLongitude());
                            getLoc.setText("Latitude:"+lat+" Longitude: "+longi);
                            msg="https://www.google.com/maps/search/?api=1&query="+lat+","+longi;
                        }
                    }
                });
            }
        });
    }
    private void requestPermission(){
        ActivityCompat.requestPermissions(this,new String[]{ACCESS_FINE_LOCATION},1);
    }
}
