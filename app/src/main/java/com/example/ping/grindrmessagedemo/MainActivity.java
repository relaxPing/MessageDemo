package com.example.ping.grindrmessagedemo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int ADD_MESSAGE_REQUEST = 1;
    private MessageViewModel messageViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton floatingActionButton = findViewById(R.id.main_float_btn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddMessageActivity.class);
                startActivityForResult(intent,ADD_MESSAGE_REQUEST);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final MessageAdapter messageAdapter = new MessageAdapter();
        recyclerView.setAdapter(messageAdapter);

        messageViewModel = ViewModelProviders.of(this).get(MessageViewModel.class);
        messageViewModel.getAllMessages().observe(this, new Observer<List<Message>>() {
            @Override
            public void onChanged(@Nullable List<Message> messages) {
                messageAdapter.setMessages(messages);
            }
        });

//        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
//            @Override
//            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
//                messageViewModel.delete(messageAdapter.getMessageAt(viewHolder.getAdapterPosition()));
//                Toast.makeText(MainActivity.this,"Message Deleted",Toast.LENGTH_SHORT).show();
//            }
//        }).attachToRecyclerView(recyclerView);

        new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                return makeMovementFlags(0,ItemTouchHelper.START | ItemTouchHelper.END);
            }

            @Override
            public boolean isItemViewSwipeEnabled() {
                return true;
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                messageViewModel.delete(messageAdapter.getMessageAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this,"Message Deleted",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                Log.i("changeed","yes");
                if(actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                    viewHolder.itemView.setBackgroundColor(Color.BLUE);
                    Log.i("changeed","yes2");
                }
                super.onSelectedChanged(viewHolder, actionState);
            }
        }).attachToRecyclerView(recyclerView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_MESSAGE_REQUEST && resultCode == RESULT_OK) {
            String name = data.getStringExtra(AddMessageActivity.EXTRA_NAME).toString();
            String message = data.getStringExtra(AddMessageActivity.EXTRA_MESSAGE).toString();
            long created_at = (long) (System.currentTimeMillis()/1000);
            Message newMessage = new Message(name, message, created_at, created_at);
            messageViewModel.insert(newMessage);

            Toast.makeText(this,"New Message Received", Toast.LENGTH_LONG).show();
        }
    }
}
