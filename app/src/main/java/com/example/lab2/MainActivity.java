package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.lab2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.h1.setOnLongClickListener(v -> {
            Toast.makeText(MainActivity.this, "men section", Toast.LENGTH_SHORT).show();
            return false;
        });


        binding.hscroll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==event.ACTION_DOWN){
                    v.setBackgroundResource(R.color.black);
                }
                else{
                    v.setBackgroundResource(R.color.white);
                }
                return false;
            }
        });
        
    }
}