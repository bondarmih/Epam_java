package edu.bondarmih.memorygame.security;

import edu.bondarmih.memorygame.security.SecurityUserDetails;
import edu.bondarmih.memorygame.entity.User;
import edu.bondarmih.memorygame.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by bondarm on 14.08.16.
 */

@Component
public class SecurityUserDetailsService implements UserDetailsService{

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userService.findUserByName(name);
        if (user == null) {
            throw new UsernameNotFoundException("UserName "+name+" not found");
        }
        return new SecurityUserDetails(user);
    }
}
