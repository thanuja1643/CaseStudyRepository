package com.pixogram.authentication.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pixogram.model.Role;
import com.pixogram.model.User;
import com.pixogram.repository.UserRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;
    
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) 
    throws UsernameNotFoundException {
        Optional<User> userop = userRepository.findByUsername(username);
        User user= userop.get();
        Set grantedAuthorities = new HashSet<>();
		/*
		 *  for (Role role : user.getRoles()){
		 * grantedAuthorities.add(new SimpleGrantedAuthority(role.getName())); }
		 */
        return new org.springframework.security.core.userdetails.User(user.getUsername(), 
        user.getPassword(), grantedAuthorities);
    }
    
}