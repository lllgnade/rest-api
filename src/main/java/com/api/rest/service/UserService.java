package com.api.rest.service;

import com.api.rest.mapper.UserMapper;
import com.api.rest.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<UserVo> allUsers() {
        List<UserVo> userList = userMapper.allUsers();
        return userList;
    }
}
