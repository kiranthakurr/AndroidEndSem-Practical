package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab2.ModelResponse.DataModel;
import com.example.lab2.ModelResponse.User;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener{

    Integer attempts=0;
    ProgressDialog progressDialog;
    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }
    EditText password,email;
TextView signuplink;
    SharedprefManager sharedPrefManager;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        password = findViewById(R.id.etpassword);
        email = findViewById(R.id.etemail);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        //        to lock the orientations of the screen
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        password = findViewById(R.id.etpassword);
        login = findViewById(R.id.btnlogin);
        signuplink=findViewById(R.id.signuplink);
//        registerlink.setOnClickListener(SignInActivity.this);
        login.setOnClickListener(this);
        sharedPrefManager = new SharedprefManager(this.getApplicationContext());  //pass the context of the application to sharedprefmanager
    }
//
//
//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v){
//            String email = email.getText().toString();
//            String password = password.getText().toString();
////                MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(SignInActivity.this);
////                materialAlertDialogBuilder.setTitle("User Registered");
////                materialAlertDialogBuilder.setIcon(R.drawable.logo);
////                materialAlertDialogBuilder.setMessage("\n     " + "Thankyou for registration!");
////                materialAlertDialogBuilder.setNeutralButton("ok", new DialogInterface.OnClickListener() {
////                    @Override
////                    public void onClick(DialogInterface dialog, int which) {
////                        materialAlertDialogBuilder.setCancelable(true);
////                        Intent intent = new Intent(SignInActivity.this,MainActivity.class);
////                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
////                        startActivity(intent);
////
////                    }
////                });
////                materialAlertDialogBuilder.show();
////            }
//        }
//        });

//    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnlogin:
                if(getAttempts()==2){
                    login.setText("Too many attempts");
                    login.setClickable(false);
                }
                else{
                    Loginuser();
                }

//                Intent intent=new Intent(LoginActivity.this, HomeActivity.class);
//                startActivity(intent);
                break;
            case R.id.signuplink:startActivity(new Intent(SignInActivity.this, Signup.class));
                break;
        }
    }

    private void Loginuser() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("LogIn");
        progressDialog.setMessage("Signing in User..");
        progressDialog.show();
        progressDialog.setCancelable(false);
        String useremail = email.getText().toString();
        String userpassword = password.getText().toString();


        if (useremail.isEmpty()) {
            email.requestFocus();
            email.setError("Please enter your email");
            progressDialog.dismiss();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(useremail).matches()) {
            email.requestFocus();
            email.setError("Please enter email correctly");
            progressDialog.dismiss();
            return;
        }
        if (userpassword.isEmpty()) {
            Toast.makeText(this, "Please enter your password ", Toast.LENGTH_LONG).show();
            progressDialog.dismiss();
            return;
        }
//        Creting the request for POST
        DataModel model = new DataModel(useremail,userpassword);
        Call<DataModel> call= RetrofitClient
                .getInstance()
                .getApi()
                .login2(model);

        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                DataModel responseFromAPi = response.body();
                progressDialog.dismiss();
                User user = responseFromAPi.getUser(); //we have assigned the user here
                if(response.isSuccessful()){
                    if(responseFromAPi.getError().equals("Incorrect Password")){
                        password.setText("");
                        setAttempts(getAttempts()+1);
                        Toast.makeText(SignInActivity.this, "Incorrect Password\nPlease try again", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else if(responseFromAPi.getError().equals("user not found")){
                        email.setText("");
                        password.setText("");
                        setAttempts(getAttempts()+1);
                        Toast.makeText(SignInActivity.this, useremail+" has not registered", Toast.LENGTH_SHORT).show();

                    }
                    else{
//                      when it is successfull then
//                        String responseString =  "\nUsername : " + user.getUsername()+"\n"+user.getEmail();

                        sharedPrefManager.SaveUser(responseFromAPi.getUser());  //this is used to save the user properties in the sharePrefManager
//sharedPrefManager.getHostelUser(res)


                        Toast.makeText(SignInActivity.this, user.getEmail()+" Login successfully", Toast.LENGTH_SHORT).show();

//                       define type

                        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
//this is used to clear the previous stack of activities so when back button pressed then previous activites
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                }else{
//                    when the response code status is 500  then this block will work
                    password.setText("");
                    email.setText("");
                    progressDialog.dismiss();
                    setAttempts(getAttempts()+1);
                    Toast.makeText(SignInActivity.this, "Incorrect Password or Email", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(SignInActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    protected void onStart(){    //when  the application will start it will check if the user is logged in then it will automatically changes to HomeActivity
        super.onStart();
        if(sharedPrefManager.isloggedIn()){
            Intent intent = new Intent(SignInActivity.this, MainActivity.class);
//this is used to clear the previous stack of activities so when back button pressed then previous activites
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}