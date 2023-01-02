package com.ideas.org.crud.security;

import com.ideas.org.crud.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class UserMain implements UserDetails {

    private String username;

    private String password;

    private boolean status;

    private Collection<? extends GrantedAuthority> authorities;

    public static UserMain build(User user) {


        List<GrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getRolEnun().name()))
                .collect(Collectors.toList());

        return new UserMain(user.getUsername(), user.getPassword(), true, authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return status;
    }
}
