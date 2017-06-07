package com.example.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.dao.UserMapper;
import com.example.model.User;
import com.example.service.UserService;

@Service
public class UserServiceImp implements UserService {
    @Resource
    private UserMapper mapper;

    public User selectUserById(int id) {
        return mapper.selectUserById(id);
    }
}
