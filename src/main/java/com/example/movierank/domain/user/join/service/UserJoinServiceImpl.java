package com.example.movierank.domain.user.join.service;

import com.example.movierank.domain.user.domain.User;
import com.example.movierank.domain.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserJoinServiceImpl implements UserJoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;



    @Override
    public void add(UserDto userDto) {
        userRepository.validateUser(userDto.getUserId());

        User user = new User(
                userDto.getUserId(),
                encoder.encode(userDto.getPassword()),
                userDto.getEmail());

        userRepository.save(user);
    }
}
