package com.codegym.backend.service;

import com.codegym.backend.model.Message;

import java.util.List;

public interface IMessageService {
    List<Message> findMessage();

    Message findById(int id);

    Message save(Message message);

    void deleteMessage(Message message);
}
