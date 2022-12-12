package com.projectm.doctors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.projectm.fragments.DoctorsFragment;
import com.projectm.R;

public class SubDoctorsActivity extends AppCompatActivity {


    ProgressBar progressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_doctors);
        getSupportFragmentManager().beginTransaction().replace(R.id.wrapper, DoctorsFragment.class,null).commit();

        String s=getIntent().getStringExtra("name");
        //String s=new String("Dentist");
        Intent intent=new Intent(SubDoctorsActivity.this,DoctorsFragment.class);
        intent.putExtra("name",s);

        progressbar = findViewById(R.id.progressBar);
    }
}