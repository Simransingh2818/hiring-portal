package org.credex.hiring.portal.service.impl;

import org.credex.hiring.portal.dao.UserDao;
import org.credex.hiring.portal.model.Login;
import org.credex.hiring.portal.model.Users;
import org.credex.hiring.portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public Users createUser(Users user) {
        return userDao.createUser(user);
    }

    @Override
    @Transactional
    public Users updateUser(Users user) {
        return userDao.updateUser(user);
    }

    @Override
    @Transactional
    public String deleteUser(int userId) {
        return userDao.deleteUser(userId);
    }

    @Override
    @Transactional
    public Users getUserById(int userId) {
        return userDao.getUserById(userId);
    }

    @Override
    @Transactional
    public Users getUserByEmailId(String emailId) {
        return userDao.getUserByEmailId(emailId);
    }

    @Override
    @Transactional
    public List<Users> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public Login authenticateUser(String emailId, String password) {
        return userDao.authenticateUser(emailId, password);
    }
}
