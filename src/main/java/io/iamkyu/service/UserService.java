package io.iamkyu.service;

import io.iamkyu.exception.DuplicateEmailException;
import io.iamkyu.model.User;
import io.iamkyu.model.UserCreateRequest;
import io.iamkyu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Kj Nam
 */
@Service
public class UserService {

    @Autowired UserRepository userRepository;
    @Autowired PasswordEncoder passwordEncoder;

    public User create(UserCreateRequest userCreateRequest) throws DuplicateEmailException {
        User user = User.create(userCreateRequest);

        if (existUser(user.getEmail())) {
            throw new DuplicateEmailException("already registered email address");
        }

        user.setEncodePassword(passwordEncoder.encode(userCreateRequest.getPassword()));
        userRepository.save(user);

        return user;
    }

    private boolean existUser(String email) {
        User user = findByEmail(email);
        return (user != null) ? true : false;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
