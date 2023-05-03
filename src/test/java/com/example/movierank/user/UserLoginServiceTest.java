package com.example.movierank.user;

import com.example.movierank.domain.exception.NotPasswordException;
import com.example.movierank.domain.exception.UserDuplicateSellerException;
import com.example.movierank.domain.user.domain.User;
import com.example.movierank.domain.user.domain.UserRepository;
import com.example.movierank.domain.user.join.service.UserDto;
import com.example.movierank.domain.user.login.UserLoginServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserLoginServiceTest {

    private final UserRepository userRepository;
    private final UserLoginServiceImpl userLoginService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserLoginServiceTest(UserRepository userRepository, UserLoginServiceImpl userLoginService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.userLoginService = userLoginService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Test
    void 로그인_확인(){
        User user = new User(
                "test", bCryptPasswordEncoder.encode("test1"), "asd@asd");

        userRepository.save(user);

        String userId = user.getUserId();
        String result = userLoginService.login("test","test1");
        
        assertEquals(userId,result);
    }

    @Test
    void 로그인_실패_아이디다름(){
        User user = new User(
                "test", bCryptPasswordEncoder.encode("test1"), "asd@asd");

        userRepository.save(user);

        UserDuplicateSellerException e = assertThrows(UserDuplicateSellerException.class, () ->
                userLoginService.login("test123", "test1")
        );

      assertEquals("아이디가 존재 하지않습니다.",e.getMessage());
    }

    @Test
    void 로그인_실패_패스워드다름(){
        User user = new User(
                "test", bCryptPasswordEncoder.encode("test1"), "asd@asd");

        userRepository.save(user);

        NotPasswordException e = assertThrows(NotPasswordException.class, () ->
                userLoginService.login("test", "test2")
        );

        assertEquals("비밀번호를 다시 확인해주세요",e.getMessage());
    }
}
