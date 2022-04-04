package com.task.firma.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Nationalized
    private String name;


    @JoinColumn
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime birthday;

    private String password;

    public User(){}

    public User(Long id, String name, Address address, LocalDateTime birthday, String password) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.birthday = birthday;
        this.password = password;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
