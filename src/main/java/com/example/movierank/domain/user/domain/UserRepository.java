package com.example.movierank.domain.user.domain;

import com.example.movierank.domain.exception.UserDuplicateSellerException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUserId(String userId);

    default void validateUser(String userId) {
        Optional<User> optionalUser = findByUserId(userId);

        if (optionalUser.isPresent()) {
            throw new UserDuplicateSellerException("이미 존재하는 아이디입니다.");
        }

    }


    default User validateCheckUser(String userId) {
        Optional<User> optionalUser = findByUserId(userId);

        return optionalUser.orElseThrow(() ->
                new UserDuplicateSellerException("아이디가 존재 하지않습니다.")
        );
    }


}
