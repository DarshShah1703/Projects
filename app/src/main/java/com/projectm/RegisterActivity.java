package com.projectm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth mAuth;

    private TextView alreadyHaveAccount;
    private EditText inputUsername,inputEmail,inputCategory,inputPassword,inputConfirmPassword;
    private Button btnRegister;
    private ProgressBar progressBar;

    String prevStarted = "first_time";
    SharedPreferences sharedpreferences ;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
//        if (mAuth.getCurrentUser() !=null){
//            finish();
//            return;
//        }

        inputUsername = findViewById(R.id.inputUsername);
        inputEmail = findViewById(R.id.inputEmail);
        //inputCategory = findViewById(R.id.inputCategory);
        inputPassword = findViewById(R.id.inputPassword);
        inputConfirmPassword = findViewById(R.id.inputConfirmPassword);

        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);

        alreadyHaveAccount = findViewById(R.id.alreadyHaveAccount);
        alreadyHaveAccount.setOnClickListener(this);

        progressBar = findViewById(R.id.progressBar);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.alreadyHaveAccount:
                startActivity(new Intent(this,LoginActivity.class));
                break;
            case R.id.btnRegister:
                registerUser();

                break;
        }
    }
    private void registerUser() {
        String userName = inputUsername.getText().toString();
        String email = inputEmail.getText().toString();
        //String category = inputCategory.getText().toString();
        String password = inputPassword.getText().toString();
        String confirmPassword = inputConfirmPassword.getText().toString();



        if (userName.isEmpty()){
            inputUsername.setError("Please provide a user-name");
            inputUsername.requestFocus();
            return;
        }
        if (email.isEmpty()){
            inputEmail.setError("Please provide a email address");
            inputEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            inputEmail.setError("Please provide valid email address");
            inputEmail.requestFocus();
            return;
        }

//        if(category.isEmpty()){
//            inputCategory.setError("Please provide a category");
//            inputCategory.requestFocus();
//            return;
//        }
//        if (!category.equals("Patient") && !category.equals("Doctor") ){
//            inputCategory.setError("Please provide valid category");
//            inputCategory.requestFocus();
//        }
        if (password.isEmpty()){
            inputPassword.setError("Please provide a password");
            inputPassword.requestFocus();
            return;
        }
        if (password.length()<6){
            inputPassword.setError("Please provide at least 6 digit password");
            inputPassword.requestFocus();
            return;
        }
        if (confirmPassword.isEmpty()){
            inputConfirmPassword.setError("Please provide a password again");
            inputConfirmPassword.requestFocus();
            return;
        }
        if (!confirmPassword.equals(password)){
            inputConfirmPassword.setError("password doesn't match.");
            inputConfirmPassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser newUserID = mAuth.getCurrentUser();
                            String userId = newUserID.getUid();
                            Users user=new Users(userName,email);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(userId)
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                Toast.makeText(RegisterActivity.this,"user has been registered successfully!",Toast.LENGTH_LONG).show();
                                                Intent intent = new Intent(RegisterActivity.this,InformationActivity.class);
                                                intent.putExtra("userid",userId);
                                                startActivity(intent);
                                                //setPrefernces();
                                            }
                                            else{
                                                Toast.makeText(RegisterActivity.this,"Fail to register. Try again...",Toast.LENGTH_LONG).show();
                                                recreate();
                                            }
                                            progressBar.setVisibility(View.GONE);                                        }
                                    });
                        }
                        else{
                            Toast.makeText(RegisterActivity.this,"Fail to register. Try again...",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }


}






//                sharedpreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
//                if (!sharedpreferences.getBoolean(prevStarted, false)) {
//                    startActivity(new Intent(RegisterActivity.this,InformationActivity.class));
//                }


//    private void setPrefernces() {
//        SharedPreferences.Editor editor = sharedpreferences.edit();
//        editor.putBoolean(prevStarted, Boolean.TRUE);
//        editor.apply();
//    }