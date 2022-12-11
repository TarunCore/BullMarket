package com.tweshllc.kskdhallmill;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<String> myMessages;
    private DatabaseReference chatRef;

    public MessageAdapter(Context mContext, ArrayList<String> myMessages) {
        this.mContext = mContext;
        this.myMessages = myMessages;
        chatRef = FirebaseDatabase.getInstance().getReference().child("Chats");
    }

    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.message_item,parent,false);
        return new MessageAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.ViewHolder holder, int position) {

        String pos = Messaging.messagePosition.get(position);
        if (pos.equals("0"))
        {
            //holder.txtLeft.setVisibility(View.VISIBLE);
            //holder.txtRight.setVisibility(View.GONE);
            holder.left.setVisibility(View.VISIBLE);
            holder.right.setVisibility(View.GONE);
            holder.txtLeft.setText(Messaging.message.get(position));
            holder.txtNameL.setText(Messaging.senderNames.get(position));

        }
        else {
            holder.right.setVisibility(View.VISIBLE);
            holder.left.setVisibility(View.GONE);
            holder.txtRight.setText(Messaging.message.get(position));
        }

        holder.txtRight.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                alert.setTitle("Delete Messages");
                alert.setMessage("Are you sure? Do you want to delete this message \nMessage: "+Messaging.message.get(position));
                alert.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        chatRef.child(Messaging.messageId.get(position)).removeValue();
                        holder.right.setVisibility(View.GONE);

                    }
                });
                alert.show();
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return Messaging.message.size();
        //return myMessages.size();


    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtLeft,txtRight,txtNameL;
        private LinearLayout left,right;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtLeft=itemView.findViewById(R.id.txtLeft);
            txtRight=itemView.findViewById(R.id.txtRight);
            left=itemView.findViewById(R.id.messageLeft);
            right=itemView.findViewById(R.id.messageRight);
            txtNameL=itemView.findViewById(R.id.txtLeftName);
        }
    }
}
