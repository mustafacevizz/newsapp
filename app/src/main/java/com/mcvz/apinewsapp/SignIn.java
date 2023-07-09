package com.mcvz.apinewsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mcvz.apinewsapp.databinding.ActivitySignInBinding;

public class SignIn extends AppCompatActivity {

    EditText edtPhone,edtPassword;
    Button btnSignIn;
    TextView forgotPassword;
    private ActivitySignInBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        edtPhone=binding.edtPhone;
        edtPassword=binding.edtPassword;
        btnSignIn=binding.btnSignIn;
        forgotPassword=binding.forgotPassword;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference table_user = database.getReference("User");
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.child(edtPhone.getText().toString()).exists()) {
                            User user = snapshot.child(edtPhone.getText().toString()).getValue(User.class);
                            if (user.getPassword().equals(edtPassword.getText().toString())) {
                                Toast.makeText(SignIn.this, "Sign in successfully", Toast.LENGTH_SHORT).show();
                                Intent signIn = new Intent(SignIn.this, MainActivity.class);
                                startActivity(signIn);
                            } else {
                                Toast.makeText(SignIn.this, "Sign in failed", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(SignIn.this, "User not exist", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}