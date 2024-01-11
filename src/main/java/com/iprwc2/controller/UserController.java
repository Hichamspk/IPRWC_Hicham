package com.iprwc2.controller;

import com.iprwc2.dao.UserDao;
import com.iprwc2.model.UserProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserDao userDao;

    @Autowired
    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProjection> getUserById(@PathVariable("id") Long id) {
        UserProjection userProjection = userDao.findUserById(id);
        return new ResponseEntity<>(userProjection, HttpStatus.OK);
    }
}
