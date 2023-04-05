package org.credex.hiring.portal.dao;

import org.credex.hiring.portal.model.ChatMessage;

import java.util.List;

public interface ChatMessageDao {

    void save(ChatMessage chatMessage);

    List<ChatMessage> getAll();
}
