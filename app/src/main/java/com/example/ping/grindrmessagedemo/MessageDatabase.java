package com.example.ping.grindrmessagedemo;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Message.class}, version = 1)
public abstract class MessageDatabase extends RoomDatabase {

    private static MessageDatabase instance;

    public abstract MessageDao messageDao();

    public static synchronized MessageDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MessageDatabase.class, "myDatabase")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomcallback)
                    .build();
        }
        return instance;
    }

    public static RoomDatabase.Callback roomcallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            new CallbackSyncTask(instance).execute();
            super.onCreate(db);
        }
    };

    public static class CallbackSyncTask extends AsyncTask<Void, Void, Void> {
        MessageDao messageDao;
        CallbackSyncTask (MessageDatabase messageDatabase) {
            messageDao = messageDatabase.messageDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            messageDao.insert(new Message("Mike", "Hi, there!", 1544378400,1544378400));
            messageDao.insert(new Message("Tim", "Hi, How are you?", 1544385600,1544385600));
            messageDao.insert(new Message("Derek", "what's up?", 1544395020,1544395020));
            return null;
        }
    }
}
