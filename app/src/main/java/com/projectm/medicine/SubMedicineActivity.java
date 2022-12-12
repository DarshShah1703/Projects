package com.projectm.medicine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.projectm.R;
import com.projectm.adapters.SubMedicineAdapter;
import com.projectm.fragments.AyurvedicFragment;

public class  SubMedicineActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    String diseaseName;
    String diseaseType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_medicine);

        tabLayout=findViewById(R.id.tabLayout1);
        viewPager=findViewById(R.id.subMedicine_viewPager);

        tabLayout.addTab(tabLayout.newTab().setText("Ayurvedic"));
        tabLayout.addTab(tabLayout.newTab().setText("Homeopathic"));
        tabLayout.addTab(tabLayout.newTab().setText("Allopathic"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final SubMedicineAdapter adapter= new SubMedicineAdapter(getSupportFragmentManager(),this,tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        diseaseName=getIntent().getStringExtra("name");
        diseaseType=getIntent().getStringExtra("type");
        Intent intent=new Intent(SubMedicineActivity.this, AyurvedicFragment.class);
        intent.putExtra("name",diseaseName);
        intent.putExtra("type",diseaseType);


    }
}