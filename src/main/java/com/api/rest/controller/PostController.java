package com.api.rest.controller;

import com.api.rest.dto.PostListResDto;
import com.api.rest.dto.PostReqDto;
import com.api.rest.dto.PostResDto;
import com.api.rest.service.PostService;
import com.api.rest.vo.PostVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@Slf4j
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping("/post")
    public PostListResDto selectAll() {
        PostListResDto postListResDto = new PostListResDto();
        try {
            List<PostVo> postList = postService.selectAll();
            postListResDto.setPostList(postList);
        }
        catch (Exception e) {
            log.error("Exception Occurred.", e);
            postListResDto.setErrorCode(-1);
        }
        return postListResDto;
    }

    @GetMapping("/post/{id}")
    public PostResDto selectById(@PathVariable("id") int id) {
        PostResDto postResDto = new PostResDto();
        try {
            PostVo postVo = postService.selectById(id);
            postResDto.setPost(postVo);
        } catch (Exception e) {
            log.error("Exception Occurred.", e);
            postResDto.setErrorCode(-1);
        }
        return postResDto;
    }

    @PostMapping("/post")
    public PostResDto create(@RequestBody PostReqDto postReqDto) {
        PostResDto postResDto = new PostResDto();
        try {
            PostVo postVo = postReqDto.getPost();
            postService.create(postVo);
        } catch (Exception e) {
            log.error("Exception Occurred.", e);
            postResDto.setErrorCode(-1);
        }
        return postResDto;
    }

    // id를 바꾸지는 못함.
    @PutMapping("/post/{id}")
    public PostResDto update(@RequestBody PostReqDto postReqDto, @PathVariable("id") int id) {
        PostResDto postResDto = new PostResDto();
        try {
            PostVo postVo = postReqDto.getPost();
            postVo.setId(id);   // 사용자가 요청에 id 값을 안 넣었을 경우에 대비해서
            postService.update(postVo);
        } catch (Exception e) {
            log.error("Exception Occurred.", e);
            postResDto.setErrorCode(-1);
        }
        return postResDto;
    }

    @DeleteMapping("/post/{id}")
    public PostResDto delete(@PathVariable("id") int id) {
        PostResDto postResDto = new PostResDto();
        try {
            postService.delete(id);
        } catch (Exception e) {
            log.error("Exception Occurred.", e);
            postResDto.setErrorCode(-1);
        }
        return postResDto;
    }
}
