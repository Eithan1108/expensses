package com.example.myapplication;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    Button logInBtn, singUpBtn, dSingUp;
    Dialog d;

    EditText etEmail, etPass, etUserName, etSingInEmail, etSingInPassword;

    FirebaseAuth mAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();


        logInBtn = (Button)findViewById(R.id.button);
        logInBtn.setOnClickListener(this);

        singUpBtn = (Button)findViewById(R.id.button2);
        singUpBtn.setOnClickListener(this);

        etSingInEmail=(EditText)findViewById(R.id.editTextTextEmailAddress);
        etSingInPassword=(EditText)findViewById(R.id.editTextTextPassword);



    }




    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    public void createLoginDialog()
    {

        d= new Dialog(this);
        d.setContentView(R.layout.sing_up_dialog);
        d.setTitle("Sing Up");
        d.setCancelable(true);
        etUserName=(EditText)d.findViewById(R.id.editTextTextPersonName2);
        etEmail=(EditText)d.findViewById(R.id.editTextTextEmailAddress3);
        etPass=(EditText)d.findViewById(R.id.editTextTextPassword3);
        dSingUp=(Button)d.findViewById(R.id.button9);




        dSingUp.setOnClickListener(this);
        d.show();


    }

    private void updateUI(FirebaseUser user) {
    }

    public  void login()
    {

    }
    boolean log = false;

    @Override
    public void onClick(View view) {



        if (view == logInBtn)
        {



            mAuth.signInWithEmailAndPassword(etSingInEmail.getText().toString(), etSingInPassword.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                Toast.makeText(MainActivity.this, "Successfully", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(MainActivity.this, home.class));


                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(MainActivity.this, "Not", Toast.LENGTH_LONG).show();
                            }

                        }


                    });




        } else if (view == singUpBtn) {

            createLoginDialog();

        } else  {


                if (etEmail.length() == 0 ||  etPass.length() == 0) {
                    Toast.makeText(this, "Empty field!", Toast.LENGTH_LONG).show();

                } else {


                        mAuth.createUserWithEmailAndPassword(etEmail.getText().toString(), etPass.getText().toString())
                                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            // Sign in success, update UI with the signed-in user's information
                                            Log.d(TAG, "createUserWithEmail:success");
                                            FirebaseUser currentUser = mAuth.getCurrentUser();
                                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                                            DatabaseReference myRef = database.getReference("Users");
                                            Users user = new Users(etUserName.getText().toString(), etEmail.getText().toString(), etPass.getText().toString());
                                            //check if empty
                                            myRef.child(currentUser.getUid()).setValue(user);
                                            log = true;


                                            d.dismiss();
                                            startActivity(new Intent(MainActivity.this, home.class));

//                                updateUI(user);


                                        } else {
                                            // If sign in fails, display a message to the user.
                                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                            updateUI(null);
                                        }

                                    }


                                });

                    if (log) {
                        startActivity(new Intent(this, home.class));
                    }
            }



        }

    }



}