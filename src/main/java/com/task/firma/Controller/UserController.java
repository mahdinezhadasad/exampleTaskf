package com.task.firma.Controller;


import com.task.firma.Respository.UserRepository;
import com.task.firma.Service.UserService;
import com.task.firma.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;


    @PostMapping("/register")
    public User processRegister(@RequestBody User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        return userRepository.save(user);

    }


    @RequestMapping(value = "/all", method = GET)
    public ResponseEntity<List<User>> getCats() {
        return ResponseEntity.ok(userService.getAllUser());
    }

    @RequestMapping(value = "/{id}", method = GET)
    public ResponseEntity<List<User>> getCatByName(@PathVariable("id") Integer id) {


            return ResponseEntity.ok(userService.getUserByZipId(id));


    }

}
