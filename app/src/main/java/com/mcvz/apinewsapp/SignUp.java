package com.mcvz.apinewsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mcvz.apinewsapp.databinding.ActivitySignUpBinding;

public class SignUp extends AppCompatActivity {

    EditText edtPhone, edtName, edtPassword;
    Button btnSignUp;
    private ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        edtPhone=binding.edtPhone;
        edtPassword=binding.edtPassword;
        edtName=binding.edtName;
        btnSignUp=binding.btnSignUp;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference table_user = database.getReference("User");

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.child(edtPhone.getText().toString()).exists()){
                            Toast.makeText(SignUp.this, "Phone Number Already Register", Toast.LENGTH_SHORT).show();
                        }else {
                            User user= new User(edtName.getText().toString(),
                                    edtPassword.getText().toString());
                            table_user.child(edtPhone.getText().toString()).setValue(user);
                            Toast.makeText(SignUp.this, "Sign Up Successfully", Toast.LENGTH_SHORT).show();
                            finish();
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