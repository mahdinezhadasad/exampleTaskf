package com.task.firma.Service;

import com.task.firma.Respository.UserRepository;
import com.task.firma.data.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    public final UserRepository userRepository;


    public UserService(UserRepository repository) {
        this.userRepository = repository;
    }


    public List<User> getAllUser(){

       return userRepository.findAll();

    }
    public List<User> getUserByZipId(Integer id) {
        return  userRepository.findByAddress_Zipcode(id);
    }

    public User save(User user){

        if(user.getName() != null){


            return userRepository.findByName(user.getName());


        }

        return userRepository.save(user);
    }


}
