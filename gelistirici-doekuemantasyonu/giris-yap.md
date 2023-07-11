# Giriş Yap

FirebaseDatabase’i uygulamamıza entegre ettikten sonra User tablosunu hedef olarak gösterdik. Girdiğimiz verilerin doğruluğunu kontrol ettik ve verilerimiz database'imizdeki verilerle eşleşiyorsa  Intent komutuyla bizi uygulamamızdaki haberlerinin olduğu activitye yönlendiriyor. Veriler Hatalıysa hata mesajı gösteriyoruz.

```
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
```
