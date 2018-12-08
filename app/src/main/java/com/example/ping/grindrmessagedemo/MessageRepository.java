package com.example.ping.grindrmessagedemo;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class MessageRepository {
    private MessageDao messageDao;
    private LiveData<List<Message>> messages;

    public MessageRepository(Application application) {
        MessageDatabase messageDatabase = MessageDatabase.getInstance(application);
        messageDao = messageDatabase.messageDao();
        messages = messageDao.getAllMessages();
    }

    public void insert(Message message) {
        new InsertMessageAsyncTask(messageDao).execute(message);
    }

    public void update(Message message) {
        new UpdateMessageAsyncTask(messageDao).execute(message);
    }

    public void delete(Message message) {
        new DeleteMessageAsyncTask(messageDao).execute(message);
    }

    public void deleteAllMessages() {
        new DeleteAllAsyncTask(messageDao).execute();
    }

    public LiveData<List<Message>> getAllMessages() {
        return messages;
    }

    public static class InsertMessageAsyncTask extends AsyncTask<Message, Void, Void> {
        MessageDao messageDao;

        InsertMessageAsyncTask(MessageDao messageDao) {
            this.messageDao = messageDao;
        }
        @Override
        protected Void doInBackground(Message... messages) {
            messageDao.insert(messages[0]);
            return null;
        }
    }

    public static class UpdateMessageAsyncTask extends AsyncTask<Message, Void, Void> {
        MessageDao messageDao;

        UpdateMessageAsyncTask(MessageDao messageDao) {
            this.messageDao = messageDao;
        }
        @Override
        protected Void doInBackground(Message... messages) {
            messageDao.update(messages[0]);
            return null;
        }
    }

    public static class DeleteMessageAsyncTask extends AsyncTask<Message, Void, Void> {
        MessageDao messageDao;

        DeleteMessageAsyncTask(MessageDao messageDao) {
            this.messageDao = messageDao;
        }
        @Override
        protected Void doInBackground(Message... messages) {
            messageDao.delete(messages[0]);
            return null;
        }
    }

    public static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        MessageDao messageDao;

        DeleteAllAsyncTask(MessageDao messageDao) {
            this.messageDao = messageDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            messageDao.deleteAllMessages();
            return null;
        }
    }
}
