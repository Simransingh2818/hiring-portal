package org.credex.hiring.portal.service;

import org.credex.hiring.portal.model.Login;
import org.credex.hiring.portal.model.Users;

import java.util.List;

public interface UserService {
    Users createUser(Users user);
    Users updateUser(Users user);
    String deleteUser(int userId);
    Users getUserById(int userId);
    Users getUserByEmailId(String emailId);
    List<Users> getAllUsers();
    Login authenticateUser(String emailId, String password);
}
