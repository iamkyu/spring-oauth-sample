package io.iamkyu.service;

import io.iamkyu.model.FrontUserDetail;
import io.iamkyu.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Kj Nam
 */
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public final FrontUserDetail loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByEmail(username);

        if(user == null) {
            throw new UsernameNotFoundException("Not Exist User");
        }

        FrontUserDetail frontUserDetail = new FrontUserDetail(user);
        return frontUserDetail;
    }
}

