package com.projectm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.MediaRouteButton;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InformationActivity extends AppCompatActivity {

    TextView txtInfo;
    EditText editTextAge,editTextBirthDate,editTextAddress;
    RadioGroup radioGroupCategory,radioGroupGender;
    RadioButton categoryRadioButton,genderRadioButton;
    Button btnSubmit;
    private ProgressBar progressBar;

    private DatabaseReference mDatabase;

    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);


        
        txtInfo = findViewById(R.id.txtInfo);

        editTextAge = findViewById(R.id.editTextAge);
        editTextBirthDate = findViewById(R.id.editTextBirthDate);
        editTextAddress = findViewById(R.id.editTextAddress);

        radioGroupCategory = findViewById(R.id.radioGroupCategory);


        radioGroupGender = findViewById(R.id.radioGroupGender);


        btnSubmit =findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addInformation();
            }
        });
        progressBar = findViewById(R.id.progressBar);
        
        userId = getIntent().getStringExtra("userid");
        mDatabase = FirebaseDatabase.getInstance().getReference("Users").child(userId);

    }
//    public void checkButton(View v){
//        int selectedIdCategory = radioGroupCategory.getCheckedRadioButtonId();
//        categoryRadioButton = (RadioButton) findViewById(selectedIdCategory);
//        if(selectedIdCategory==-1){
//            Toast.makeText(InformationActivity.this,"select a category", Toast.LENGTH_SHORT).show();
//        }
//        else{
//            Toast.makeText(InformationActivity.this,categoryRadioButton.getText(), Toast.LENGTH_SHORT).show();
//        }
//
//        int selectedIdGender = radioGroupGender.getCheckedRadioButtonId();
//        genderRadioButton = (RadioButton) findViewById(selectedIdGender);
//        if(selectedIdGender==-1){
//            Toast.makeText(InformationActivity.this,"select a gender", Toast.LENGTH_SHORT).show();
//        }
//        else{
//            Toast.makeText(InformationActivity.this,genderRadioButton.getText(), Toast.LENGTH_SHORT).show();
//        }
//    }

    private void addInformation() {
        
        int selectedIdCategory = radioGroupCategory.getCheckedRadioButtonId();
        categoryRadioButton = (RadioButton) findViewById(selectedIdCategory);
        String category = categoryRadioButton.getText().toString();

        String age = editTextAge.getText().toString();
        String birthdate = editTextBirthDate.getText().toString();

        int selectedIdGender = radioGroupGender.getCheckedRadioButtonId();
        genderRadioButton = (RadioButton) findViewById(selectedIdGender);
        String gender = genderRadioButton.getText().toString();
        
        String address = editTextAddress.getText().toString();



        if (category.isEmpty()){
            int lastChildPos=radioGroupCategory.getChildCount()-1;
            ((RadioButton)radioGroupCategory.getChildAt(lastChildPos)).setError("Please Select a category");
            radioGroupCategory.requestFocus();
        }
        progressBar.setVisibility(View.VISIBLE);
        try {
            //Users users = new Users(age,birthdate,address);

            mDatabase.child("category").setValue(category);
            mDatabase.child("age").setValue(age);
            mDatabase.child("birthdate").setValue(birthdate);
            mDatabase.child("gender").setValue(gender);
            mDatabase.child("address").setValue(address);
            Toast.makeText(this, "Details added Successfully", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(InformationActivity.this,LoginActivity.class));
        }catch (DatabaseException e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
       
    }


}
