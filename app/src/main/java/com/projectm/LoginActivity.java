package com.projectm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private int RC_SIGN_IN = 1;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mClient;

    private TextView logo,forgotPassword,textdontHaveAccount,textsignUp,currentUser;
    private EditText inputEmail,inputPassword;
    private Button btnLogin,btnGoogle,btnFacebook;
    private ProgressBar progressbar;

//    String prevStarted = "first_time";
//    SharedPreferences sharedpreferences ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        currentUser=findViewById(R.id.currentUser);

        logo=findViewById(R.id.logo);

        textdontHaveAccount=findViewById(R.id.textdontHaveAccount);

        textsignUp=findViewById(R.id.textsignUp);
        textsignUp.setOnClickListener(this);

        inputEmail=findViewById(R.id.inputEmail);
        inputPassword=findViewById(R.id.inputPassword);

        btnLogin=findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        forgotPassword=findViewById(R.id.forgotPassword);
        forgotPassword.setOnClickListener(this);

        btnGoogle=findViewById(R.id.btnGoogle);
        btnGoogle.setOnClickListener(this);

        btnFacebook=findViewById(R.id.btnFacebook);
        btnFacebook.setOnClickListener(this);

        progressbar=findViewById(R.id.progressBar);


        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mClient= GoogleSignIn.getClient(this,gso);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser()!= null) {
//            sharedpreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
//            if (!sharedpreferences.getBoolean(prevStarted, false)) {
//                startActivity(new Intent(LoginActivity.this,InformationActivity.class));
//            }
//            else{
//                Intent intent = new Intent(this,HomeActivity.class);
//                startActivity(intent);
//            }
            Intent intent = new Intent(this,HomeActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.forgotPassword:
                passwordReset();
                break;
            case R.id.btnLogin:
                authenticateUser();
                break;
            case R.id.textsignUp:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
            case R.id.btnGoogle:
                
                signIn();
                break;
            case R.id.btnFacebook:
                break;
        }
    }

    //google signIn
    private void signIn() {
        progressbar.setVisibility(View.VISIBLE);
        Intent signInIntent= mClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,@NonNull Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completeTask) {
        try {
            GoogleSignInAccount acc = completeTask.getResult(ApiException.class);
            Toast.makeText(this, "Signed in Successfully", Toast.LENGTH_SHORT).show();
            progressbar.setVisibility(View.VISIBLE);
            FirebaseGoogleAuth(acc);
            registerUser();
            //setPrefernces();


        } catch (ApiException e) {
            recreate();
            Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show();
            progressbar.setVisibility(View.VISIBLE);
            FirebaseGoogleAuth(null);

        }
    }

//    private void setPrefernces() {
//        SharedPreferences.Editor editor = sharedpreferences.edit();
//        editor.putBoolean(prevStarted, Boolean.TRUE);
//        editor.apply();
//    }

    // store in firebase
    private void FirebaseGoogleAuth(GoogleSignInAccount acct) {
        AuthCredential authCredential = GoogleAuthProvider.getCredential(acct.getIdToken(),null);
        mAuth.signInWithCredential(authCredential).addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "SuccessFull", Toast.LENGTH_SHORT).show();
                    FirebaseUser user=mAuth.getCurrentUser();
                    //updateUI(user);
                    progressbar.setVisibility(View.GONE);
                    navigateToHomeActivity();
                }
                else{
                    Toast.makeText(LoginActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    progressbar.setVisibility(View.GONE);
//                  updateUI(null);
                }
            }
        });

    }

    // registration user via google sinIn
    private void registerUser() {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        String userName;
        final String[] category = new String[1];
        String email;
        String password= getAlphaNumericString();
        if (account != null){
            userName= account.getDisplayName();
            email= account.getEmail();
            progressbar.setVisibility(View.VISIBLE);
//            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
//            alertDialogBuilder.setMessage("Chose your category..");
//            alertDialogBuilder.setTitle("Alert..");
//            alertDialogBuilder.setPositiveButton("Patient",
//                    new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface arg0, int arg1) {
//                            progressbar.setVisibility(View.GONE);
//                            category[0] = new String("Patient" );
//                        }
//                    });
//
//            alertDialogBuilder.setNegativeButton("Doctor",new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    progressbar.setVisibility(View.GONE);
//                    category[0] = new String("Doctor" );
//                }
//            });
//
//            AlertDialog alertDialog = alertDialogBuilder.create();
//            alertDialog.show();
            
            if (email != null && password != null) {
                mAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Users user=new Users(userName,email);

                                    FirebaseDatabase.getInstance().getReference("Users")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()){
                                                        Toast.makeText(LoginActivity.this,"user has been registered successfully!",Toast.LENGTH_LONG).show();
                                                    }
                                                    else{
                                                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LoginActivity.this);
                                                        alertDialogBuilder.setMessage("Fail to register. Try again...");
                                                        AlertDialog alertDialog = alertDialogBuilder.create();
                                                        alertDialog.show();
                                                        //Toast.makeText(LoginActivity.this,"Fail to register. Try again...",Toast.LENGTH_LONG).show();
                                                    }
                                                    progressbar.setVisibility(View.GONE);                                        }
                                            });
                                }
                                else{
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LoginActivity.this);
                                    alertDialogBuilder.setMessage("User is already registered.");
                                    AlertDialog alertDialog = alertDialogBuilder.create();
                                    alertDialog.show();
                                    //Toast.makeText(LoginActivity.this," User is already registered.",Toast.LENGTH_LONG).show();
                                    progressbar.setVisibility(View.GONE);
                                }
                            }
                        });
            }
        }



    }

    private void navigateToHomeActivity() {
        finish();
        Intent intent=new Intent(this,HomeActivity.class);
        startActivity(intent);
    }

    //to reset password
    private void passwordReset() {
        String email=inputEmail.getText().toString();
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
        progressbar.setVisibility(View.VISIBLE);
        mAuth.sendPasswordResetEmail(email)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(LoginActivity.this, "Email sent", Toast.LENGTH_SHORT).show();
                        progressbar.setVisibility(View.GONE);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginActivity.this, "Fail to send email", Toast.LENGTH_SHORT).show();
                        progressbar.setVisibility(View.GONE);
                    }
                });
    }

    //authentication
    private void authenticateUser() {
        String email=inputEmail.getText().toString();
        String password=inputPassword.getText().toString();

        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Fill empty fields", Toast.LENGTH_LONG).show();
            return;
        }
        progressbar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            //setPrefernces();
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);

                            startActivity(intent);

                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                        }
                        progressbar.setVisibility(View.GONE);
                    }
                });
    }

    // Auto generate password.
    static String getAlphaNumericString()
    {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(6);

        for (int i = 0; i < 6; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();
    }


}








//    private void updateUI(FirebaseUser user) {
//        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
//        if (account!=null)
//        {
//            String userName=account.getDisplayName();
//            String email=account.getEmail();
//
//            Toast.makeText(this, userName+email, Toast.LENGTH_SHORT).show();
//        }
//    }



//    GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
//        if (account != null){
//                String name= account.getDisplayName();
//                String email= account.getEmail();
//                currentUser.setText(name);
//                }

