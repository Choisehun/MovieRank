package com.example.movierank.domain.user.login;

import com.example.movierank.domain.exception.NotPasswordException;
import com.example.movierank.domain.user.domain.User;
import com.example.movierank.domain.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLoginServiceImpl implements UserLoginService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public String login(String userId, String password) {
        User user = userRepository.validateCheckUser(userId);

        if(!user.isValidPassword(bCryptPasswordEncoder,password)){
            throw new NotPasswordException("비밀번호를 다시 확인해주세요");
        }

        return user.getUserId();
    }
}
