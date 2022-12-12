package com.projectm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity {

    FirebaseUser user;
    DatabaseReference reference;
    String userID;
    private LinearLayout imgLayout,layout,textViewLayout,editTextLayout,btnLayout;
    private TextView textUserName,textEmail,textAge,textBirthDate,textGender,textCategory,textAddress;
    private EditText editTextUserName,editTextEmail,editTextAge,editTextBirthDate,editTextAddress;
    private Button btnEdit,btnSubmit;
    private ProgressBar progressbar;
    private ImageView profileImg;

    RadioGroup radioGroupCategory,radioGroupGender;
    RadioButton categoryRadioButton,genderRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        imgLayout = findViewById(R.id.imgLayout);
        layout = findViewById(R.id.layout);
        textViewLayout = findViewById(R.id.textViewLayout);
        editTextLayout = findViewById(R.id.editTextLayout);
        btnLayout = findViewById(R.id.btnLayout);

        textUserName = findViewById(R.id.textUserName);
        textEmail = findViewById(R.id.textEmail);
        textAge = findViewById(R.id.textAge);
        textBirthDate = findViewById(R.id.textBirthDate);
        textGender = findViewById(R.id.textGender);
        textCategory = findViewById(R.id.textCategory);
        textAddress = findViewById(R.id.textAddress);

        editTextUserName = findViewById(R.id.editTextUserName);
        //editTextEmail = findViewById(R.id.editTextEmail);
        editTextAge = findViewById(R.id.editTextAge);
        editTextBirthDate = findViewById(R.id.editTextBirthDate);
        editTextAddress = findViewById(R.id.editTextAddress);

        //radioGroupCategory = findViewById(R.id.radioGroupCategory);


        radioGroupGender = findViewById(R.id.radioGroupGender);


        btnEdit = findViewById(R.id.btnEdit);
        btnSubmit = findViewById(R.id.btnSubmit);
        profileImg = findViewById(R.id.profileImg);
        progressbar = findViewById(R.id.progressBar);

        showUserData();

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                updateUI();

            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUserData();
            }
        });
    }

    private void updateUI() {
        textViewLayout.setVisibility(View.GONE);
        editTextLayout.setVisibility(View.VISIBLE);
        btnSubmit.setVisibility(View.VISIBLE);
        btnEdit.setVisibility(View.GONE);

        editTextUserName.setText(textUserName.getText().toString());
        editTextAge.setText(textAge.getText().toString());
        editTextBirthDate.setText(textBirthDate.getText().toString());
        editTextAddress.setText(textAddress.getText().toString());
    }

    private void updateUserData() {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        progressbar.setVisibility(View.VISIBLE);
        if (account != null){
            Toast.makeText(this, "Go to Google account manager..", Toast.LENGTH_SHORT).show();
        }
        else{

            String username = editTextUserName.getText().toString();
//            int selectedIdCategory = radioGroupCategory.getCheckedRadioButtonId();
//            categoryRadioButton = (RadioButton) findViewById(selectedIdCategory);
//            String category = categoryRadioButton.getText().toString();

            String age = editTextAge.getText().toString();
            String birthdate = editTextBirthDate.getText().toString();

            int selectedIdGender = radioGroupGender.getCheckedRadioButtonId();
            genderRadioButton = (RadioButton) findViewById(selectedIdGender);
            String gender = genderRadioButton.getText().toString();

            String address = editTextAddress.getText().toString();
            HashMap userMap = new HashMap();
            userMap.put("username",username);
            userMap.put("age",age);
            userMap.put("birthdate",birthdate);
            userMap.put("gender",gender);
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            userID=user.getUid();
            reference = FirebaseDatabase.getInstance().getReference("Users");
            reference.child(userID).updateChildren(userMap)
                    .addOnCompleteListener(new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if (task.isSuccessful()){
                                Toast.makeText(ProfileActivity.this, "Data has successfully updated", Toast.LENGTH_SHORT).show();
                                textViewLayout.setVisibility(View.VISIBLE);
                                editTextLayout.setVisibility(View.GONE);
                                btnEdit.setVisibility(View.VISIBLE);
                                btnSubmit.setVisibility(View.GONE);

                                editTextUserName.setText("");
                                editTextAge.setText("");
                                editTextBirthDate.setText("");
                                editTextAddress.setText("");

                                recreate();
                            }
                            else{
                                Toast.makeText(ProfileActivity.this, "Data has not updated", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private void showUserData() {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        progressbar.setVisibility(View.VISIBLE);
        if (account != null){
            String name = account.getDisplayName();
            String email = account.getEmail();
            Uri img = account.getPhotoUrl();

            textUserName.setText(name);
            textEmail.setText(email);
            profileImg.setImageURI( img );
            String s = img.toString();
            textAge.setText(s);
            progressbar.setVisibility(View.GONE);

        }
        else{

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            userID=user.getUid();
            reference = FirebaseDatabase.getInstance().getReference("Users");
            reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Users usersDetails = new Users();
                    usersDetails = snapshot.getValue(Users.class);
                    if (usersDetails != null){
                        String userName = usersDetails.username;
                        String email = usersDetails.email;
                        String age = usersDetails.age;
                        String birthdate =usersDetails.birthdate;
                        String gender = usersDetails.gender;
                        String category = usersDetails.category;
                        String address = usersDetails.address;
                        textUserName.setText(userName);
                        textEmail.setText(email);
                        textAge.setText(age);
                        textBirthDate.setText(birthdate);
                        textGender.setText(gender);
                        textCategory.setText(category);
                        textAddress.setText(address);
                        progressbar.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(ProfileActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    progressbar.setVisibility(View.GONE);
                }
            });
        }


    }
}