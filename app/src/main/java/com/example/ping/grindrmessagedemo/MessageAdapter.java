package com.example.ping.grindrmessagedemo;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.allenliu.badgeview.BadgeView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

        SimpleDateFormat format;
        long updated_at = currentMessage.getUpdated_at();
        if(isToday(updated_at)) {
            format =  new SimpleDateFormat( "HH:mm" );
        } else {
            format =  new SimpleDateFormat( "MM/dd" );
        }
        String d = format.format(updated_at * 1000);
        holder.textViewTime.setText(d);

        holder.badgeViewBadge.setTextColor(Color.parseColor("#191918"));
        holder.badgeViewBadge.setBadgeBackground(Color.parseColor("#fcd846"));
        holder.badgeViewBadge.setTextSize(10);
        holder.badgeViewBadge.setBadgeCount(1);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void setMessages(List<Message> messages){
        this.messages = messages;
        notifyDataSetChanged();
    }

    public Message getMessageAt(int position) {
        return messages.get(position);
    }

    class MessageHolder extends RecyclerView.ViewHolder{
        private TextView textViewName;
        private TextView textViewMessage;
        private TextView textViewTime;
        private BadgeView badgeViewBadge;
        public MessageHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.message_text_view_name);
            textViewMessage = itemView.findViewById(R.id.message_text_view_message);
            textViewTime = itemView.findViewById(R.id.message_text_view_quantity);
            badgeViewBadge = itemView.findViewById(R.id.badge);
        }
    }

    public boolean isToday(long current) {
        SimpleDateFormat pattern = new SimpleDateFormat("yyyy-MM-dd");
        String today = pattern.format(new Date());
        String now = pattern.format(current * 1000);
        if(today.equals(now)) {
            return true;
        }
        return false;
    }
}
