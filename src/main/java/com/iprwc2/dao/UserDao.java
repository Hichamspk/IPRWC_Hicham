package com.iprwc2.dao;

import com.iprwc2.model.UserProjection;
import com.iprwc2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.iprwc2.model.User;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDao {
    @Autowired
    private UserRepository userRepository;

    public UserProjection findUserById(Long id){
        return userRepository.findUserProjectionById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}

