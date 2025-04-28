package com.samyprojects.rps.futura_back.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.samyprojects.rps.futura_back.model.Utilisator;



public class CustomUserDetails implements UserDetails, CredentialsContainer {

    private Utilisator utilisator;

    public CustomUserDetails(Utilisator utilisator) {
        this.utilisator = utilisator;
    }

    public Utilisator getUtilisator() {
        return utilisator;
    }

    // Implement all UserDetails methods
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return utilisator.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return utilisator.getPassword();
    }

    @Override
    public String getUsername() {
        return utilisator.getEmail();
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
        return true;
    }

    @Override
    public void eraseCredentials() {
        this.utilisator.setPassword(null);  // Securely erase the password
    }
}