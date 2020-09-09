 uploadb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if (TextUtils.isEmpty(username.getText().toString())){
                  Toast.makeText(AddShayariAC.this, "Enter Your Name", Toast.LENGTH_SHORT).show();
              }else if (TextUtils.isEmpty(addshayari.getText().toString())){
                  Toast.makeText(AddShayariAC.this, "Add Shayari", Toast.LENGTH_SHORT).show();
              }
              else {
                  registration();
              }

            }
        });
    }

    private void registration() {
        pd.setTitle("Uploading");
        pd.show();
        HashMap<String, Object> map = new HashMap<>();
        map.put("username", username.getText().toString());
        map.put("addshayari", addshayari.getText().toString());
        FirebaseDatabase.getInstance().getReference().child("User").push()
                .setValue(map)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.i("User", "onComplete");
                        pd.dismiss();
                        Intent intent = new Intent(AddShayariAC.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(AddShayariAC.this, "Uploaded Successful", Toast.LENGTH_SHORT).show(); }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("User", "onFailure");
            }
        });
        recieveData();
    }

    private void recieveData() {

    }

}