package com.example.ping.grindrmessagedemo;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface MessageDao {

    @Insert
    void insert(Message message);

    @Delete
    void delete(Message message);

    @Update
    void update(Message message);

    @Query("DELETE FROM message")
    void deleteAllMessages();

    @Query("SELECT * FROM message ORDER BY updated_at desc")
    LiveData<List<Message>> getAllMessages();
}
