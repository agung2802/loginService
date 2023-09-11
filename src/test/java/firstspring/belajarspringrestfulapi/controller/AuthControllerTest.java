/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package firstspring.belajarspringrestfulapi.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
/**
 *
 * @author Agung
 */
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import firstspring.belajarspringrestfulapi.entity.User;
import firstspring.belajarspringrestfulapi.model.WebResponse;
import firstspring.belajarspringrestfulapi.repository.UserRepository;
import firstspring.belajarspringrestfulapi.security.Bcrypt;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp(){
        userRepository.deleteAll();
    }

    // @Test
    // void loginFailed() throws Exception{
    //     LoginUserRequest request = new LoginUserRequest();
    //     request.setUsername("test");
    //     request.setPassword("test");
    //     mockMvc.perform(
    //         post("/api/auth/login").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request))
    //     ).andExpectAll(status().isUnauthorized()
    //     ).andDo(result -> {
            
    //         WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>(){
    //         });
            
    //         assertNotNull(response.getErrors());
    //     });
    // }

    // @Test
    // void loginWrongPassword() throws Exception{
    //     User user = new User();
    //     user.setUsername("test");
    //     user.setPassword(Bcrypt.hashpw("test", Bcrypt.gensalt()));
    //     user.setName("testing");
    //     userRepository.save(user);

    //     LoginUserRequest request = new LoginUserRequest();
    //     request.setUsername("test");
    //     request.setPassword("testing");
    //     mockMvc.perform(
    //         post("/api/auth/login").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request))
    //     ).andExpectAll(status().isUnauthorized()
    //     ).andDo(result -> {
            
    //         WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>(){
    //         });
            
    //         assertNotNull(response.getErrors());
    //     });
    // }
    
    // @Test
    // void loginWrongUsername() throws Exception{
    //     User user = new User();
    //     user.setUsername("test");
    //     user.setPassword(Bcrypt.hashpw("test", Bcrypt.gensalt()));
    //     user.setName("testing");
    //     userRepository.save(user);

    //     LoginUserRequest request = new LoginUserRequest();
    //     request.setUsername("testing");
    //     request.setPassword("test");
    //     mockMvc.perform(
    //         post("/api/auth/login").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request))
    //     ).andExpectAll(status().isUnauthorized()
    //     ).andDo(result -> {
            
    //         WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>(){
    //         });
            
    //         assertNotNull(response.getErrors());
    //     });
    // }

    // @Test
    // void loginSuccess() throws Exception{

    //     User user = new User();
    //     user.setUsername("test");
    //     user.setPassword(Bcrypt.hashpw("test", Bcrypt.gensalt()));
    //     user.setName("testing");
    //     userRepository.save(user);

    //     LoginUserRequest request = new LoginUserRequest();
    //     request.setUsername("test");
    //     request.setPassword("test");
    //     // request.setPassword(Bcrypt.hashpw("test", Bcrypt.gensalt()));
    //     mockMvc.perform(
    //         post("/api/auth/login").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request))
    //     ).andExpectAll(status().isOk()
    //     ).andDo(result -> {
            
    //         WebResponse<TokenResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>(){
    //         });
            
    //         assertNull(response.getErrors());
    //         assertNotNull(response.getData().getToken());
    //         assertNotNull(response.getData().getExpiredAt());

    //         User userDb = userRepository.findById("test").orElse(null);
    //         assertNotNull(userDb);
    //         assertEquals(userDb.getToken(), response.getData().getToken());
    //         assertEquals(userDb.getTokenExpiredAt(), response.getData().getExpiredAt());
    //     });
    // }

    @Test
    void logoutSuccess() throws Exception{

        User user = new User();
        user.setUsername("test");
        user.setPassword(Bcrypt.hashpw("test", Bcrypt.gensalt()));
        user.setName("testing");
        user.setToken("test");
        user.setTokenExpiredAt(System.currentTimeMillis() + 1000000);
        
        userRepository.save(user);

        mockMvc.perform(
                delete("/api/auth/logout")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
        ).andExpectAll(
            status().isOk()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>(){});
            
            assertEquals("OK", response.getData());

            User userDb = userRepository.findById("test").orElse(null);

            assertNull(userDb.getToken());
            assertNull(userDb.getTokenExpiredAt());
        });
    }

}
