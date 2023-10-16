package com.codegym.backend.controller;

import com.codegym.backend.model.Message;
import com.codegym.backend.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController("/api/private")
public class WebsocketController {
    @Autowired
    IMessageService iMessageService;

    //    @MessageExceptionHandler()
    @MessageMapping("/messages")
    @SendTo("/topic/messages")
    public Message broadcastNews(Message mess) {
        return this.iMessageService.save(mess);
    }
}
