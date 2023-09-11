package firstspring.belajarspringrestfulapi.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import firstspring.belajarspringrestfulapi.entity.User;
import firstspring.belajarspringrestfulapi.model.LoginUserRequest;
import firstspring.belajarspringrestfulapi.model.TokenResponse;
import firstspring.belajarspringrestfulapi.repository.UserRepository;
import firstspring.belajarspringrestfulapi.security.Bcrypt;
import jakarta.transaction.Transactional;

@Service
public class AuthService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public TokenResponse login(LoginUserRequest request){
        validationService.validate(request);
        
        User user = userRepository.findById(request.getUsername())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password wrong"));

        if (Bcrypt.checkpw(request.getPassword(), user.getPassword())) {
            //success
            user.setToken(UUID.randomUUID().toString());
            user.setTokenExpiredAt(next30Days());
            userRepository.save(user);

            return TokenResponse.builder()
                    .token(user.getToken())
                    .expiredAt(user.getTokenExpiredAt())
                    .build();
        } else{
            //error
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password wrongh");
        }
        
    }

    @Transactional
    public void logout(User user){
        user.setToken(null);
        user.setTokenExpiredAt(null);

        userRepository.save(user);
    }

    private Long next30Days(){
        return System.currentTimeMillis() + (1000 * 60 * 24 * 30);
    }
}
