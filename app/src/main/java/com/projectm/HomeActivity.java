package com.projectm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;


import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.projectm.doctors.DoctorsActivity;
import com.projectm.hospital.HospitalActivity;
import com.projectm.medicine.MedicineActivity;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    GoogleSignInAccount account;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    FirebaseAuth mAuth;
    TextView currentUser;

    FirebaseUser user;
    DatabaseReference reference;
    String userID;

    ProgressBar progressBar;
    DrawerLayout homeDrawer;
    NavigationView navigationView;

    Toolbar toolbar;
    ImageView medicineBtn,doctorsBtn,hospitalsBtn;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();


        progressBar =findViewById(R.id.progressBar);
        homeDrawer = findViewById(R.id.homeDrawer);
        navigationView = findViewById(R.id.navigationView);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        getMenu();

        currentUser = findViewById(R.id.currentUser);
        setCurrentUSer();

        medicineBtn=findViewById(R.id.medicineBtn);
        medicineBtn.setOnClickListener(this);

        doctorsBtn=findViewById(R.id.doctorsBtn);
        doctorsBtn.setOnClickListener(this);

        hospitalsBtn=findViewById(R.id.hospitalsBtn);
        hospitalsBtn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.medicineBtn:
                startActivity(new Intent(HomeActivity.this, MedicineActivity.class));
                break;
            case R.id.doctorsBtn:
                startActivity(new Intent(HomeActivity.this, DoctorsActivity.class));
                break;
            case R.id.hospitalsBtn:
                startActivity(new Intent(HomeActivity.this, HospitalActivity.class));
                break;
        }
    }

    //for menu
    private void getMenu() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toggle=new ActionBarDrawerToggle(this,homeDrawer,toolbar,R.string.navigation_open,R.string.navigation_close);
        homeDrawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                getItemForMenu(item);

                return true;
            }
        });
    }
    private void getItemForMenu(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.homeMenu:
                Toast.makeText(getApplicationContext(), "Home panel is Open", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                homeDrawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.profileMenu:
                Toast.makeText(getApplicationContext(), "Profile panel is Open", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
                String s = currentUser.getText().toString();
                intent.putExtra("email",s);
                startActivity(intent);
                homeDrawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.logout:
                signOut();
                homeDrawer.closeDrawer(GravityCompat.START);
                break;
        }
    }

    // current user textView
    private void setCurrentUSer() {
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        gsc = GoogleSignIn.getClient(this,gso);

        account = GoogleSignIn.getLastSignedInAccount(this);

        user= FirebaseAuth.getInstance().getCurrentUser();

        userID = user.getUid();
        
        if (account != null){
            String name= account.getDisplayName();
            currentUser.setText("Welcome"+" "+name);
        }
        else
        {
            reference = FirebaseDatabase.getInstance().getReference("Users");
            reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Users usersDetails = new Users();
                    usersDetails = snapshot.getValue(Users.class);
                    if (usersDetails != null){

                        String userName = usersDetails.username;
                        currentUser.setText("Welcome"+" "+ userName);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(HomeActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    // for logout...
    private void signOut() {
        progressBar.setVisibility(View.VISIBLE);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure, You want to Logout");
        alertDialogBuilder.setTitle("Alert..");
                alertDialogBuilder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        progressBar.setVisibility(View.GONE);
                                        mAuth.getInstance().signOut();
                                        startActivity(new Intent(HomeActivity.this,LoginActivity.class));

                                    }
                                });
                                Toast.makeText(getApplicationContext(), "Logged out", Toast.LENGTH_SHORT).show();
                            }
                        });

                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        progressBar.setVisibility(View.GONE);
                        recreate();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    private void navigateToLoginPage() {
        startActivity(new Intent(HomeActivity.this,LoginActivity.class));
    }
}