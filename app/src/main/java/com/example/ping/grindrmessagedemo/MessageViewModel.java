package com.example.ping.grindrmessagedemo;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class MessageViewModel extends AndroidViewModel {
    private MessageRepository messageRepository;
    private LiveData<List<Message>> messages;

    public MessageViewModel(@NonNull Application application) {
        super(application);
        messageRepository = new MessageRepository(application);
        messages = messageRepository.getAllMessages();
    }

    public void insert(Message message) {
        messageRepository.insert(message);
    }

    public void update(Message message) {
        messageRepository.update(message);
    }

    public void delete(Message message) {
        messageRepository.delete(message);
    }

    public void deleteAllMessages() {
        messageRepository.deleteAllMessages();
    }

    public LiveData<List<Message>> getAllMessages() {
        return messageRepository.getAllMessages();
    }
}
