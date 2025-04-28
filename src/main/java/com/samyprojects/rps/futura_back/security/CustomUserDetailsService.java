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
import com.samyprojects.rps.futura_back.service.UtilisatorService;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    
    public UtilisatorService utilisatorService;

    @Autowired
    CustomUserDetailsService(UtilisatorService utilisatorService) {
        this.utilisatorService = utilisatorService;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return loadUserByEmail(email);  // Use email-based login
    }

    
    // public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {

    //     Utilisator utilisator = userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("this email adress does not exit, sorry !"));
    //     return new User(utilisator.getEmail(), utilisator.getPassword(), mapRolesToAuthorities(utilisator.getRoles()));
    // }

    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        Utilisator utilisator = utilisatorService.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("This email address does not exist, sorry!"));
    
        return new CustomUserDetails(utilisator);
    }


    private Collection<GrantedAuthority>mapRolesToAuthorities(List<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    
}
