package com.codegym.backend.service.impl;

import com.codegym.backend.model.Message;
import com.codegym.backend.repository.IMessageRepository;
import com.codegym.backend.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService implements IMessageService {
    @Autowired
    IMessageRepository messageRepository;

    /**
     * @return
     */
    @Override
    public List<Message> findMessage() {
        return messageRepository.findMessage();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Message findById(int id) {
        return messageRepository.findMessageById(id);
    }

    /**
     * @param message
     * @return
     */
    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    /**
     * @param message
     */
    @Override
    public void deleteMessage(Message message) {
        messageRepository.delete(message);
    }
}
