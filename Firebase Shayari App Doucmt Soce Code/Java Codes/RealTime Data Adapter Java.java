

//myadapter

package com.sharepunjabishayari;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class myadapter extends FirebaseRecyclerAdapter<Model,myadapter.myviewholder>
{


    private Context context;


    public myadapter(@NonNull FirebaseRecyclerOptions<Model> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int i, @NonNull final Model model)
    {
        holder.username.setText(model.getUsername());
        holder.shayaritext.setText(model.getAddshayari());

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
        Button copybtn,sharebtn;
         TextView username,shayaritext;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            shayaritext = itemView.findViewById(R.id.shayaritext);
            username = itemView.findViewById(R.id.UserNameShow);
            copybtn = itemView.findViewById(R.id.copy);
            sharebtn = itemView.findViewById(R.id.share);

        }
    }
}

