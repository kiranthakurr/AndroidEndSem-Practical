package com.example.lab2;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.lab2.ModelResponse.User;

public class SharedprefManager {private static String SHARED_PREF_NAME = "Hostel_management";  //the corresponding to the shared preference
    private SharedPreferences sharedPreferences;
    Context context;
    private SharedPreferences.Editor editor ;

    public SharedprefManager(Context context){   //the constructor to get the context of the application
        this.context=context;
    }

    public void SaveUser(User user){
//        this method will save all the values corressponding to the keys
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.putString("_id", user.get_id());
        editor.putString("username", user.getUsername());
        editor.putString("avatar", user.getAvatar());
        editor.putString("email", user.getEmail());
        editor.putString("rollNumber",user.getRollNumber());
        editor.putString("branch", user.getBranch());
        editor.putString("address", user.getAddress());
        editor.putString("phone", user.getPhone());
        editor.putBoolean("logged",true);   //to check wheater the user is logged in or not
        editor.apply();
    }

    public boolean isloggedIn() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("logged",false);
//if the user is logged in then the shared preference will contain the logged key with true valu
// else the false default value will be returned
    }

    public void setAdmin(String userType){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.putString("userType",userType);
        editor.apply();
    }

    public String getAdmin(){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString("userType",null);
    }

    public User getUser(){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(sharedPreferences.getString("_id","-1"),
                sharedPreferences.getString("username",null),
                sharedPreferences.getString("avatar",null),
                sharedPreferences.getString("email",null),
                sharedPreferences.getString("rollNumber",null),
                sharedPreferences.getString("branch",null),
                sharedPreferences.getString("address",null),
                sharedPreferences.getString("phone",null)
        );

    }

    public void logout(){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }


}
