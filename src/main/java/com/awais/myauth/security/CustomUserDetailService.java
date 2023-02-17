package com.awais.myauth.security;

import com.awais.myauth.entities.User;
import com.awais.myauth.exceptions.ResourceNotFoundException;
import com.awais.myauth.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user= userRepo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User","email :"+username,0));

       return user;
    }
}
