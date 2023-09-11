/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package firstspring.belajarspringrestfulapi.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import firstspring.belajarspringrestfulapi.entity.User;
import firstspring.belajarspringrestfulapi.model.RegisterUserRequest;
import firstspring.belajarspringrestfulapi.model.UpdateUserRequest;
import firstspring.belajarspringrestfulapi.model.UserResponse;
import firstspring.belajarspringrestfulapi.repository.UserRepository;
import firstspring.belajarspringrestfulapi.security.Bcrypt;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Agung
 */
@Service
@Slf4j
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ValidationService validationService;
    
    @Transactional
    public void register(RegisterUserRequest request){
        
        validationService.validate(request);
        
        if(userRepository.existsById(request.getUsername())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already registered");
        }
        
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(Bcrypt.hashpw(request.getPassword(), Bcrypt.gensalt()));
        user.setName(request.getName());
        
        userRepository.save(user);
    }

    public UserResponse get(User user){
        return UserResponse.builder()
                .username(user.getUsername())
                .name(user.getName())
                .build();
    }

    @Transactional
    public UserResponse update(User user, UpdateUserRequest request){
        
        log.info("REQUEST", request);
        validationService.validate(request);

        // User user = new User();
        if (Objects.nonNull(request.getName())) {
            user.setName(request.getName());
        }

        if (Objects.nonNull(request.getPassword())) {
            user.setPassword(Bcrypt.hashpw(request.getPassword(), Bcrypt.gensalt()));
        }

        userRepository.save(user);

        return UserResponse.builder()
                .name(user.getName())
                .username(user.getUsername()).build();
    }
}
