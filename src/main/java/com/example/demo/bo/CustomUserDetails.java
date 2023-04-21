package com.example.demo.bo;

import com.example.demo.entity.UsersDemo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {
    private String username;
    private String password;
    private Boolean enabled;
    private List<GrantedAuthority> auths;

    public CustomUserDetails(UsersDemo usersDemo, List<GrantedAuthority> auths) {
        this.username = usersDemo.getUsername();
        this.password = usersDemo.getPassword();
        this.enabled = usersDemo.getEnabled();
        this.auths = auths;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return this.auths;
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
        return enabled;
    }
}
