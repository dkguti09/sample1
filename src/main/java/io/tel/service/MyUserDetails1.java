package io.tel.service;


import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails1 implements UserDetails{
    
    
    
    
    private String userName;
    
    public MyUserDetails1(String userName) {
        this.userName = userName;
    }
    
    public MyUserDetails1() {
        
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
    
        return "pass";
    }

    @Override
    public String getUsername() {
        
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
    
        return false;
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
        
        return true;
    }
    

}
