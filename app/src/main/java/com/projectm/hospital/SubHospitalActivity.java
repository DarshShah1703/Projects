package com.projectm.hospital;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.projectm.R;
import com.projectm.fragments.HospitalFragment;


public class SubHospitalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_hospital);
        getSupportFragmentManager().beginTransaction().replace(R.id.wrapper, HospitalFragment.class,null).commit();
//        String s=getIntent().getStringExtra("name");
//        //String s=new String("Dentist");
//        Intent intent=new Intent(SubHospitalActivity.this,DoctorsFragment.class);
//        intent.putExtra("name",s);
    }
}