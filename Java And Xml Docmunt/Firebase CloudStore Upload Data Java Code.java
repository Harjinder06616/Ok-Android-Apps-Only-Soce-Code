  HashMap<String, Object> map = new HashMap<>();
                map.put("username", username.getText().toString());
                map.put("addshayari", addshayari.getText().toString());
                FirebaseDatabase.getInstance().getReference().child("User").push()
                        .setValue(map)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Log.i("User", "onComplete");
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("User", "onFailure");
                    }
                });
            }
        });
    }
}