//


package com.sharepunjabishayari;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class AddShayariAC extends AppCompatActivity {
    EditText username;
    EditText addshayari;
    Button uploadb;
    ProgressDialog pd;
    FirebaseFirestore db;
    DocumentReference documentReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shayari_a_c);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("ADD SHAYARI");
        username = findViewById(R.id.UserName);
        addshayari = findViewById(R.id.ShayariAddBox);
        uploadb = findViewById(R.id.Uploadbtn);
        pd = new ProgressDialog(this);
        db = FirebaseFirestore.getInstance();
        documentReference = db.collection("Users").document();



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