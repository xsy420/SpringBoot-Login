package com.example.backend.service.impl;

import com.example.backend.mapper.UserMapper;
import com.example.backend.entity.User;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    @CachePut(value = "user", key = "#user.username")
    public User insert(User user) {
        userMapper.insert(user);
        return user;
    }

    @Override
    @Cacheable(value = "user", key = "#username")
    public User findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }

    @Override
    @CachePut(value = "user", key = "#user.username")
    public User updateUser(User user) {
        userMapper.updateById(user);
        return user;
    }

    @Override
    @CacheEvict(value = "user", key = "#username")
    public int deleteUserByUsername(String username) {
        return userMapper.deleteByMap(Map.of("username", username));
    }

}
