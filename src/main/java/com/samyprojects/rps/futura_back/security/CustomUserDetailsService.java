package com.samyprojects.rps.futura_back.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.samyprojects.rps.futura_back.model.Role;
import com.samyprojects.rps.futura_back.model.Utilisator;
import com.samyprojects.rps.futura_back.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    public UserRepository userRepository;

    @Autowired
    CustomUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Utilisator utilisator = userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("this user does not exit, sorry !"));
        // return new User(utilisator.getName(), utilisator.getPassword(), mapRolesToAuthorities(utilisator.getRoles()));
            // You can check if the username passed is actually an email.
        return loadUserByEmail(username);  // Use email-based login
    }

    
    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {

        Utilisator utilisator = userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("this email adress does not exit, sorry !"));
        return new User(utilisator.getEmail(), utilisator.getPassword(), mapRolesToAuthorities(utilisator.getRoles()));
    }


    private Collection<GrantedAuthority>mapRolesToAuthorities(List<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    
}
