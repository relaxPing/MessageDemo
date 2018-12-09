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
            long now = System.currentTimeMillis()/1000;
            messageDao.insert(new Message("Tim", "Hi, there!", 1544196000,1544196000));
            messageDao.insert(new Message("Kelly", "What you up to?", 1544196000,1544196000));
            messageDao.insert(new Message("Scott", "Hi, Cutie!", 1544196000,1544196000));
            messageDao.insert(new Message("Mike", "Hi, How are you?", 1544314800,1544314800));
            messageDao.insert(new Message("Derek", "what's up?", now, now));
            return null;
        }
    }
}
