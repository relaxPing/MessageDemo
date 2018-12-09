package com.example.ping.grindrmessagedemo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "message")
public class Message {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private String message;

    private long created_at;

    private long updated_at;

    public Message(String name, String message, long created_at, long updated_at) {
        this.name = name;
        this.message = message;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getCreated_at() {
        return created_at;
    }

    public String getMessage() {
        return message;
    }

    public long getUpdated_at() {
        return updated_at;
    }
}
