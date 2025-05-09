package com.samyprojects.security;

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
import com.samyprojects.rps.futura_back.repository.UtilisatorRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    public UtilisatorRepository userRepository;

    @Autowired
    CustomUserDetailsService(UtilisatorRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Utilisator utilisator = userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("this user does not exit, sorry !"));
        return new User(utilisator.getName(), utilisator.getPassword(), mapRolesToAuthorities(utilisator.getRoles()));
    }


    private Collection<GrantedAuthority>mapRolesToAuthorities(List<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    
}
