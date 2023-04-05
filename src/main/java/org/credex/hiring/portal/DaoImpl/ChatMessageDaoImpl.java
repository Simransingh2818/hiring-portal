package org.credex.hiring.portal.DaoImpl;

import org.credex.hiring.portal.dao.ChatMessageDao;
import org.credex.hiring.portal.model.ChatMessage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChatMessageDaoImpl implements ChatMessageDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void save(ChatMessage chatMessage) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(chatMessage);
        transaction.commit();
    }

    public List<ChatMessage> getAll() {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List<ChatMessage> chatMessages = session.createQuery("FROM ChatMessage", ChatMessage.class).getResultList();
        transaction.commit();
        return chatMessages;
    }
}

