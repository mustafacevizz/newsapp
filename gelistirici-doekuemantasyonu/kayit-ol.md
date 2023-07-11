# Kayıt ol

Kullanıcı kayıt olurken kullanılmamış bir telefon numarası girmeli. EditTextler istenilen şekilde doldurulduğunda Firebase database’e kayıt olan kullanıcının verileri ekleniyor ve kullanıcıya mesaj veriliyor.

FirebaseDatabase database = FirebaseDatabase.getInstance(); DatabaseReference table\_user = database.getReference("User");

```
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
```

}
