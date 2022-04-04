package com.task.firma.Controller;

import static org.junit.Assert.*;


import com.task.firma.Controller.UserController;
import com.task.firma.Respository.UserRepository;
import com.task.firma.Service.UserService;
import com.task.firma.data.Address;
import com.task.firma.data.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.verify;

import java.net.URI;
import java.util.Collections;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.Mockito.times;
import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    @Autowired
    private JacksonTester<User> json;


    @Before
    public void setup(){

        User user =getUser();
        given(userService.save(any())).willReturn(user);
        given(userService.getUserByZipId(any())).willReturn(Collections.singletonList(user));
        given(userService.getAllUser()).willReturn(Collections.singletonList(user));


    }



    @Test
    public void createUser() throws Exception {
        User car = getUser();
        mockMvc.perform(
                        post(new URI("/users/register"))
                                .content(json.write(car).getJson())
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void  listUser() throws Exception {

        User car = getUser();
        createUser();
        mockMvc.perform(
                        get(new URI("/users/all"))
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
        verify(userService, times(1)).getAllUser();




    }



    private User getUser(){


        User user = new User();


        user.setPassword("kkkk");
        user.setBirthday(LocalDateTime.now());
        Address address = new Address();

        address.setName("josefplatz");
        address.getZipcode(5);

        user.setName("mahdi");
        user.setAddress(address);

        return user;
    }


}