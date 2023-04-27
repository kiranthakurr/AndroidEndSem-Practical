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
import com.example.lab2.ModelResponse.DataModel2;
import com.example.lab2.ModelResponse.User;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Signup extends AppCompatActivity implements View.OnClickListener{;
    EditText name, email, password;
    Button signup;
    TextView loginlink;
    ProgressDialog progressDialog ;   //this will give the background box also
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_signup);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

//        getSupportActionBar().hide(); //to hide the action bar shown at the top of the app
        email = findViewById(R.id.etemail);
        password = findViewById(R.id.etpassword);
//        phone=findViewById(R.id.etphone);
        name=findViewById(R.id.etname);
        signup = findViewById(R.id.btnsignup);
        loginlink=findViewById(R.id.loginlink);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateUser();
            }
        });
        loginlink.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.btnSignUp:
//                Toast.makeText(this, "button", Toast.LENGTH_SHORT).show();
//                CreateUser();
//             break;
            case R.id.loginlink:
                switchonlogin();
                break;
        }
    }

    private void CreateUser() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Registering user");
        progressDialog.setMessage("Registering user..");
        progressDialog.show();
        progressDialog.setCancelable(false);
        String useremail = email.getText().toString();
        String userpassword = password.getText().toString();
        String username = name.getText().toString();

        if (useremail.isEmpty()) {
            email.requestFocus();
            email.setError("Please enter your roll number");
            progressDialog.dismiss();
            return;
        }
        if (username.isEmpty()) {
            name.requestFocus();
            name.setError("Please enter your userName");
            progressDialog.dismiss();

            return;
        }
//        if (!useremail.contains("@gmail.com")) {
//            email.requestFocus();
//            email.setError("Please enter correct mail");
//            progressDialog.dismiss();
//            return;
//        }
//        if (!Patterns.EMAIL_ADDRESS.matcher(useremail).matches()) {
//            email.requestFocus();
//            email.setError("Please enter email correctly");
//            progressDialog.dismiss();
//
//            return;
//        }
        if (userpassword.isEmpty()) {
            Toast.makeText(this, "Please enter your college ", Toast.LENGTH_LONG).show();
            progressDialog.dismiss();

            return;
        }
//        else if(!containsNumbers(userpassword)) {
//            Toast.makeText(this, "Password must contain numbers", Toast.LENGTH_LONG).show();
//            progressDialog.dismiss();
//        }


//        else if(userpassword.length() < 8){
//            Toast.makeText(this, "Minimum length Required is 8 ", Toast.LENGTH_LONG).show();
//            progressDialog.dismiss();
//            return;
//        }
//        else {
////            TO FIND THE SPECIAL CHARACTER IN PASSWORD
//            int count=0;
//            for (int i = 0; i < userpassword.length(); i++) {
//
//                // Checking the character for not being a
//                // letter,digit or space
//                if (!Character.isDigit(userpassword.charAt(i)) && !Character.isLetter(userpassword.charAt(i))
//                        && !Character.isWhitespace(userpassword.charAt(i))) {
//                    // Incrementing the countr for spl
//                    // characters by unity
//                    count++;
//                }
//            }
//            // When there is no special character encountered
//            if (count == 0){
//                Toast.makeText(this, "Password must contain special characters", Toast.LENGTH_SHORT).show();
//                progressDialog.dismiss();
//                return ;
//            }
//        }
//    HERE THE OBJECT OF RETROFIT IS BEING MADE (that was made seprately in the video)
//    connect laptop wwith the Hostpot of the phone  and on wifi clicking change it to private discoverable mode
//    MAKE SURE TO CHANGE THE DISCOVERABLE DEVICE (PRIVATE MODE) IN WIFI SETTING OF PARTICULAR NETWORK
//    use the current ip address and start the server before and the set the ip address in the mongodb and
//    use CMD ipconfig to get the ip address and then set the WIFI LAN ADAPTER Wifi IPV$ address
//    use "http://IP_ADDRESS:PORT_NUMBER/"
//
//        CREATE THE INSTANCE OF THE DATA-MODAL
//        DATA MODAL WILL CONTAIN THE INFORMATION TO BE SEND FROM THE CLIENT SIDE IN THE POST REQUEST
        DataModel2 modal = new DataModel2(useremail, userpassword,username);
        Call<DataModel2> call = RetrofitClient
                .getInstance()
                .getApi()
                .Signup2(modal);
//ENQUEUE THE REQUEST AND USE callback<DataModal>()
        call.enqueue(new Callback<DataModel2>() {
            @Override
            public void onResponse(Call<DataModel2> call, Response<DataModel2> response) {
                progressDialog.dismiss();

//                receive the response from the API DATA MODAL OBJECT
                DataModel2 responseFromAPi = response.body();
                if(response.isSuccessful()){
//                  this will run when status (200)
                    User user = responseFromAPi.getUser();  // now the user will contain the response user info (username,email,password)
                   if(responseFromAPi.getMessage().equals("true")){
                        //Call intent to transefer to new login activity

//                        Intent intent = new Intent(Signup.this, SignInActivity.class);
////                        //this is used to clear the previous stack of activities so when back button pressed then previous activites
//                        startActivity(intent);
//                       intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


//                        finish();
                MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(Signup.this);
                materialAlertDialogBuilder.setTitle("User Registered");
                materialAlertDialogBuilder.setIcon(R.drawable.logo);
                materialAlertDialogBuilder.setMessage("\n     " + "Thankyou for registration!");
                materialAlertDialogBuilder.setNeutralButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        materialAlertDialogBuilder.setCancelable(true);
//                        Intent intent = new Intent(Signup.this,MainActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                        startActivity(intent);
                        email.setText("");
                        password.setText("");
                        name.setText("");
                        name.setFocusable(true);
                    }
                });
                materialAlertDialogBuilder.show();
////            }
//        }

                    }

                }else{
//                  this block will execute for status (500) when error is thrown
                    Toast.makeText(Signup.this, "Something went wrong\nTry again later", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<DataModel2> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(Signup.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void switchonlogin() {
        Intent intent = new Intent(Signup.this,SignInActivity.class);
        startActivity(intent);
    }

    public static boolean containsNumbers(String string) {
        if (string == null || string.isEmpty()) {
            return false;
        }
        for (int i = 0; i < string.length(); ++i) {
            if (Character.isDigit(string.charAt(i))) {
                return true;
            }
        }
        return false;
    }
}