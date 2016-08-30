package edu.bondarmih.memorygame.security;

import edu.bondarmih.memorygame.entity.Role;
import edu.bondarmih.memorygame.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by bondarm on 14.08.16.
 */
public class SecurityUserDetails extends User implements UserDetails {

    public SecurityUserDetails(User user) {
        if(user != null)
        {
            this.setId(user.getId());
            this.setName(user.getName());
            this.setPassword(user.getPassword());
            this.setRoles(user.getRoles());
        }
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        List<Role> userRoles = this.getRoles();
        if(userRoles != null)
        {
            for (Role role : userRoles) {
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
                authorities.add(authority);
            }
        }
        return authorities;
    }

    public String getUsername() {
        return super.getName();
    }

    public String getPassword() {
        return super.getPassword();
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }
}
