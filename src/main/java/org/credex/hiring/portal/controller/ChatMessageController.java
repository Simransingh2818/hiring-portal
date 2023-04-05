package org.credex.hiring.portal.controller;

import org.credex.hiring.portal.dao.ChatMessageDao;
import org.credex.hiring.portal.model.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

public class ChatMessageController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private ChatMessageDao chatMessageDao;

    @CrossOrigin(origins = "http://localhost:4200")
    @MessageMapping("/chat/message")
    public void save(ChatMessage chatMessage) {
        chatMessageDao.save(chatMessage);
        messagingTemplate.convertAndSend("/topic/messages", chatMessage);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @SubscribeMapping("/messages")
    public List<ChatMessage> getAll() {
        return chatMessageDao.getAll();
    }
}
