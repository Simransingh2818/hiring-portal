package org.credex.hiring.portal.DaoImpl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.credex.hiring.portal.dao.UserDao;
import org.credex.hiring.portal.model.Login;
import org.credex.hiring.portal.model.Users;
import org.credex.hiring.portal.service.BeanUtility;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Users createUser(Users user) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.save(user);
            session.flush();
        } catch (DataAccessException e) {
            throw new RuntimeException("Error creating user", e);
        }
        return user;
    }

    @Override
    public Users updateUser(Users user) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Users oldUserRec = session.get(Users.class, user.getUserId());
            BeanUtility.copyNonNullProperties(user,oldUserRec );
            session.save(oldUserRec);
            session.flush();
            return oldUserRec;
        } catch (DataAccessException e) {
            throw new RuntimeException("Error updating user", e);
        }
    }

    @Override
    public String deleteUser(int userId) {
        try {
            Boolean aBoolean = deleteById(Users.class, userId);
            return "This {userId} has been deleted";
        } catch (DataAccessException e) {
            throw new RuntimeException("Error deleting user", e);
        }
    }

    public <T> boolean deleteById (Class<T> clazz, int id) {
        Session session = sessionFactory.getCurrentSession();
        Object ob = session.load(clazz, id);
        session.delete(ob);
        session.flush();
        return true;
    }

    @Override
    public Users getUserById(int userId) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Users user = session.get(Users.class, userId);
            return user;
        } catch (DataAccessException e) {
            throw new RuntimeException("Error getting user by id", e);
        }
    }

    @Override
    public Users getUserByEmailId(String emailId) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query<Users> query = session.createQuery("FROM Users u WHERE u.emailId = :emailId", Users.class)
                    .setParameter("emailId", emailId);
            Users user = query.uniqueResult();
            return user;
        } catch (DataAccessException e) {
            throw new RuntimeException("Error getting user by email", e);
        }
    }

    @Override
    public List<Users> getAllUsers() {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query<Users> query = session.createQuery("FROM Users", Users.class);
            List<Users> users = query.getResultList();
            return users;
        } catch (DataAccessException e) {
            throw new RuntimeException("Error getting all users", e);
        }
    }

    @Override
    public Login authenticateUser(String emailId, String password) {
        try (Session session = sessionFactory.openSession()) {
            Query<Users> query = session.createQuery(
                            "FROM Users u WHERE u.emailId = :emailId AND u.password = :password", Users.class)
                    .setParameter("emailId", emailId)
                    .setParameter("password", password);
            Users user = query.uniqueResult();
            if (user != null) {
                String roles = session.createQuery(
                                "SELECT u.roleId FROM Users u WHERE u.userId = :userId", String.class)
                        .setParameter("userId", user.getUserId())
                        .uniqueResult();
                String token = Jwts.builder()
                        .setSubject(user.getEmailId())
                        .claim("claims", roles)
                        .setExpiration(new Date(System.currentTimeMillis() + 864_000_000)) // 10 days
                        .signWith(SignatureAlgorithm.HS256, "secret-key")
                        .compact();
                return new Login(token,user.getRoleId(),user.getEmailId(), user.getUserId());
            } else {
                throw new RuntimeException("User not found");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error authenticating user: " + e.getMessage(), e);
        }
    }

}
