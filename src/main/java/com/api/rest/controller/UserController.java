package com.api.rest.controller;

import com.api.rest.service.UserService;
import com.api.rest.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    //응답 결과에 body 이외에 Http 상태 코드와 헤더를 설정
    @GetMapping("/users")
    public ResponseEntity<List<UserVo>> allUsers(){
        List<UserVo> userList;
        try {
            userList = userService.allUsers();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.<UserVo>emptyList()); //500
        }
        return ResponseEntity.ok(userList);
    }

}
