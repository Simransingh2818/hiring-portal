package org.credex.hiring.portal.dao;

import org.credex.hiring.portal.model.Users;
import org.credex.hiring.portal.service.BeanUtility;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;



    @Override
    @Transactional
    public Users createUser(Users user) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.save(user);
        }finally {
            session.flush();
        }
        return user;

    }

    @Override
    @Transactional
    public Users updateUser(Users user) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Users oldUserRec = session.get(Users.class, user.getUserId());
            BeanUtility.copyNonNullProperties(user,oldUserRec );
            session.save(oldUserRec);
            return oldUserRec;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    @Transactional
    public String  deleteUser(int userId) {

        Boolean aBoolean = deleteById(Users.class, userId);
        return "This {userId} has been deleted";
    }

    public <T> boolean deleteById (Class<T> clazz, int id) {
        Session session = sessionFactory.getCurrentSession();
        Object ob = (Object) session.load(clazz, id);
        session.delete(ob);
        session.flush();
        return true;
    }

    @Override
    @Transactional
    public Users getUserById(int userId) {
        Session session = sessionFactory.getCurrentSession();
        Users user = session.get(Users.class, userId);
        return user;
    }
    @Override
    @Transactional
    public List<Users> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        Query<Users> query = session.createQuery("FROM Users", Users.class);
        List<Users> users = query.getResultList();
        return users;
    }
}
