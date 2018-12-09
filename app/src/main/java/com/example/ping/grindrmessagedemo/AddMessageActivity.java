package com.example.ping.grindrmessagedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class AddMessageActivity extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextMessage;
    private int created_at;
    private int updated_at;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_message);

        editTextName = findViewById(R.id.add_message_name);
        editTextMessage = findViewById(R.id.add_message_message);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Message");
    }
}
