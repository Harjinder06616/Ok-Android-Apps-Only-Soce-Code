package com.sharepunjabishayari;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class myadapter extends FirebaseRecyclerAdapter<Model,myadapter.myviewholder>
{


    private Context context;
    DatabaseReference databaseSnapshot;


    public myadapter(@NonNull FirebaseRecyclerOptions<Model> options, Context context) {
        super(options);
        this.context = context;

    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholder holder, int i, @NonNull final Model model)
    {
        holder.username.setText(model.getUsername());
        holder.shayaritext.setText(model.getAddshayari());
		
//       Like Button OnClickListener Text Code Start
        isLike(model.getUsername(),holder.likebtn);
        holder.likebtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Assert")
            @Override
            public void onClick(View v) {
                if (holder.likebtn.getTag().equals("like")){
                    FirebaseUser firebaseUser = null;
                    assert false;
                    if (holder.likebtn.getTag().equals("like")) {
                        FirebaseDatabase.getInstance().getReference().child("Likes").child(model.getUsername())
                        .child(firebaseUser.getUid()).setValue(true);
                    }
                }else {
                    FirebaseUser firebaseUser = null;
                    assert false;
                    FirebaseDatabase.getInstance().getReference().child("Likes").child(model.getUsername())
                            .child(firebaseUser.getUid()).removeValue();
                }
            }
        });
		
//      Like Button OnClickListener Text Code End
 		
        holder.copybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label",model.getAddshayari());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(context,  "Copy", Toast.LENGTH_SHORT).show();
            }
        });

        holder.sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent txtIntent = new Intent(Intent.ACTION_SEND);
                txtIntent .setType("text/plain");
                txtIntent .putExtra(Intent.EXTRA_SUBJECT,"shayari");
                txtIntent .putExtra(Intent.EXTRA_TEXT,model.getAddshayari());
                context.startActivity(Intent.createChooser(txtIntent,"Share"));
                Toast.makeText(context,  "Share", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_reshowshayari,parent,false);
         return new myviewholder(view);
         
    }

    static class myviewholder extends RecyclerView.ViewHolder
    {
        ImageButton likebtn;
        FirebaseAuth  AuthLike;
        ImageButton copybtn,sharebtn;
         TextView username,shayaritext;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            shayaritext = itemView.findViewById(R.id.shayaritext);
            username = itemView.findViewById(R.id.UserNameShow);
            likebtn = itemView.findViewById(R.id.like);
            copybtn = itemView.findViewById(R.id.copy);
            sharebtn = itemView.findViewById(R.id.share);
            
        }
    }
	
//   Like Button Text code Start   
    private  void  isLike (String username, final ImageView imageView){
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference()
                .child("Likes")
                .child(username);
        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("Assert")
            @Override
            public void  onDataChange(@NonNull DataSnapshot snapshot)  {
                assert firebaseUser != null;
                if (databaseSnapshot.child(firebaseUser.getUid()).equals("")) {
                    imageView.setImageResource(R.drawable.ic_like24);
                    imageView.setTag("like");
                } else {
                    imageView.setImageResource(R.drawable.ic_like);
                    imageView.setTag("liked");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void nrLike (final TextView likes, String username) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Likes")
                .child(username);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                likes.setText(databaseSnapshot.getKey());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//          Like Button Text code End 		
    }
}

