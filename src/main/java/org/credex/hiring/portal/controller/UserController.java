package org.credex.hiring.portal.controller;

import org.credex.hiring.portal.dao.UserDao;
import org.credex.hiring.portal.model.Users;
import org.credex.hiring.portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

   @Autowired
   private UserService userService;
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public Users createUser(@Validated @RequestBody Users user) {
        return userService.createUser(user);

    }
    @GetMapping("/get")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/getById/{userId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Users getUserById(@PathVariable int userId) {
        return userService.getUserById(userId);
    }


    @PutMapping("/update")
    @CrossOrigin(origins = "http://localhost:4200")
    public Users updateUser( @RequestBody Users user) {

        return userService.updateUser(user);
    }

    @DeleteMapping("/delete/{userId}")
    public String deleteUser(@PathVariable("userId") int userId) {
       return userService.deleteUser(userId);
    }
}
