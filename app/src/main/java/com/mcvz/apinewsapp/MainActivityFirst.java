package com.mcvz.apinewsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mcvz.apinewsapp.databinding.ActivityMainFirstBinding;

public class MainActivityFirst extends AppCompatActivity {
    Button btnSignIn,btnSignUp;
    TextView txtSlogan;
    private ActivityMainFirstBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainFirstBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        btnSignIn=binding.btnSignIn;
        btnSignUp=binding.btnSignUp;

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signIn=new Intent(MainActivityFirst.this, SignIn.class);
                startActivity(signIn);
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUp=new Intent(MainActivityFirst.this, SignUp.class);
                startActivity(signUp);
            }
        });
    }
}