package com.example.movierank.user;

import com.example.movierank.domain.user.domain.User;
import com.example.movierank.domain.user.domain.UserRepository;
import com.example.movierank.domain.user.join.service.UserDto;
import com.example.movierank.domain.user.join.service.UserJoinServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserJoinServiceTest {

    private final UserRepository userRepository;
    private final UserJoinServiceImpl userJoinService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserJoinServiceTest(UserRepository userRepository, UserJoinServiceImpl userJoinService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.userJoinService = userJoinService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Test
    void 회원가입_확인() {
        UserDto userDto = new UserDto(
                "test", "test1", "asd@asd"
        );

        userJoinService.add(userDto);

        Optional<User> userId = userRepository.findByUserId("test");


        Assertions.assertEquals("test", userDto.getUserId());
        assertTrue(bCryptPasswordEncoder.matches("test1", userId.get().getPassword()));
    }
}
