package com.task.firma.Respository;


import com.task.firma.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    @Query("select u from User u where u.address.zipcode = ?1")
    List<User> findByAddress_Zipcode(Integer id);


    User findByName(String name);


}
