package com.example.ping.grindrmessagedemo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageHolder> {
    private List<Message> messages = new ArrayList<>();
    @NonNull
    @Override
    public MessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View messageView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_item,null,false);
        return new MessageHolder(messageView);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageHolder holder, int position) {
        Message currentMessage = messages.get(position);
        holder.textViewName.setText(currentMessage.getName());
        holder.textViewMessage.setText(currentMessage.getMessage());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void setMessages(List<Message> messages){
        this.messages = messages;
        notifyDataSetChanged();
    }

    class MessageHolder extends RecyclerView.ViewHolder{
        private TextView textViewName;
        private TextView textViewMessage;
        public MessageHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.message_text_view_name);
            textViewMessage = itemView.findViewById(R.id.message_text_view_message);
        }
    }
}
