package com.projectm.doctors;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.projectm.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class DoctorsActivity extends AppCompatActivity {

    FusedLocationProviderClient fusedLocationProviderClient;
    TextView cityName;

    private final static int REQUEST_CODE=100;

    ProgressBar progressbar;
    ImageView btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);
        cityName=findViewById(R.id.location);
        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this);
        cityName.setVisibility(View.GONE);

        btn1=findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLastLocation();
            }
        });

        progressbar = findViewById(R.id.progressBar);
    }
    private void getLastLocation( ) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){

            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location!=null){

                        Geocoder geocoder=new Geocoder(DoctorsActivity.this, Locale.getDefault());
                        List<Address> addresses=null;

                        try {
                            addresses=geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                            cityName.setText(addresses.get(0).getLocality());
                            String s=cityName.getText().toString();
                            Intent intent=new Intent(DoctorsActivity.this, SubDoctorsActivity.class);
                            intent.putExtra("name",s);
                            startActivity(intent);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
        else{

            askPermission();
        }
    }

    private void askPermission() {
        ActivityCompat.requestPermissions(DoctorsActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if ( requestCode == REQUEST_CODE){
            if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                getLastLocation();
            }
            else{
                Toast.makeText(DoctorsActivity.this,"Please provide required permission",Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}