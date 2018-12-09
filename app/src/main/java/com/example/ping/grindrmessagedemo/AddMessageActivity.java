package com.example.ping.grindrmessagedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AddMessageActivity extends AppCompatActivity {
    public static final String EXTRA_NAME = "com.example.ping.grindrmessagedemo.EXTRA_NAME";
    public static final String EXTRA_MESSAGE = "com.example.ping.grindrmessagedemo.EXTRA_MESSAGE";
    private EditText editTextName;
    private EditText editTextMessage;
    private int created_at;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_message);

        editTextName = findViewById(R.id.add_message_name);
        editTextMessage = findViewById(R.id.add_message_message);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Message");
    }

    private void saveMessage() {
        String name = editTextName.getText().toString();
        String message = editTextMessage.getText().toString();

        if(name.trim().isEmpty() || message.trim().isEmpty()) {
            Toast.makeText(this,"Please Input a Name and a Message",Toast.LENGTH_LONG).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_NAME,name);
        data.putExtra(EXTRA_MESSAGE, message);
        setResult(RESULT_OK,data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_message_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.message_save :
                saveMessage();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
