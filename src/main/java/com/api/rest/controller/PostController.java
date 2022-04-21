package com.api.rest.controller;

import com.api.rest.dto.ResultCode;
import com.api.rest.dto.PostListResDto;
import com.api.rest.dto.PostReqDto;
import com.api.rest.dto.PostResDto;
import com.api.rest.service.PostService;
import com.api.rest.vo.PostVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class PostController {

    @Autowired
    PostService postService;

    /**
     * 쿼리스트링 조건에 맞는 Post를 불러온다.
     * 어떤 쿼리스트링도 입력하지 않으면 모든 Post를 불러온다.
     *
     * @param userId 해당 userId를 가진 Post
     * @param title 해당 내용을 title에 포함하고 있는 Post
     * @param body  해당 내용을 body에 포함하고 있는 Post
     * @return PostList 와 ResultCode 를 포함한 Dto. 에러가 없을 시에는 리스트만 반환한다.
     */
    @GetMapping("/post")
    public PostListResDto selectWithQuery(@RequestParam(required = false) Integer userId,
                                           @RequestParam(required = false) String title,
                                           @RequestParam(required = false) String body) {

        PostListResDto postListResDto = new PostListResDto();
        List<PostVo> postList = null;

        // vo를 생성해 검색 필터로 사용
        PostVo postFilter = new PostVo();
        if(userId != null)
            postFilter.setUserId(userId); //int는 null이 될 수 없으므로 파라미터를 Integer 로 받아줌
        if(title != null)
            postFilter.setTitle(title);
        if(body != null)
            postFilter.setBody(body);

        try {
            postList = postService.selectByQuery(postFilter);
        } catch (DataAccessException e) {
            log.error("Exception Occurred.", e);
            postListResDto.setResultCode(ResultCode.UNKNOWN_DATABASE_ERROR);
            return postListResDto;
        } catch (Exception e) {
            log.error("Exception Occurred.", e);
            postListResDto.setResultCode(ResultCode.UNKNOWN_ERROR);
            return postListResDto;
        }
        postListResDto.setPostList(postList);
        return postListResDto;
    }

    @GetMapping("/post/{id}")
    public PostResDto selectById(@PathVariable("id") int id) {
        PostResDto postResDto = new PostResDto();
        PostVo postVo = null;
        try {
            postVo = postService.selectById(id);
        } catch (DataAccessException e) {
            log.error("Exception Occurred.", e);
            postResDto.setResultCode(ResultCode.UNKNOWN_DATABASE_ERROR);
            return postResDto;
        } catch (Exception e) {
            log.error("Exception Occurred.", e);
            postResDto.setResultCode(ResultCode.UNKNOWN_ERROR);
            return postResDto;
        }
        postResDto.setPost(postVo);
        return postResDto;
    }


    @PostMapping("/post")
    public PostResDto create(@RequestBody PostReqDto postReqDto) {
        PostResDto postResDto = new PostResDto();
        try {
            PostVo postVo = postReqDto.getPost();
            postService.create(postVo);
        } catch (DuplicateKeyException e) {
            log.error("Exception Occurred.", e);
            postResDto.setResultCode(ResultCode.DUPLICATE_KEY);
            return postResDto;
        } catch (DataIntegrityViolationException e) {
            log.error("Exception Occurred.", e);
            postResDto.setResultCode(ResultCode.BAD_DATA_REQUEST);
            return postResDto;
        } catch (DataAccessException e) {
            log.error("Exception Occurred.", e);
            postResDto.setResultCode(ResultCode.UNKNOWN_DATABASE_ERROR);
            return postResDto;
        } catch (Exception e) {
            log.error("Exception Occurred.", e);
            postResDto.setResultCode(ResultCode.UNKNOWN_ERROR);
            return postResDto;
        }
        postResDto.setResultCode(ResultCode.SUCCESSFUL);
        return postResDto;
    }

    // id를 변경하지는 못함.
    @PutMapping("/post/{id}")
    public PostResDto update(@RequestBody PostReqDto postReqDto, @PathVariable("id") int id) {
        PostResDto postResDto = new PostResDto();
        int updatedRows = -1;
        try {
            PostVo postVo = postReqDto.getPost();
            postVo.setId(id);   // 이 값으로 SQL where 절에서 검색
            updatedRows = postService.update(postVo);
        } catch (DataIntegrityViolationException e) {
            log.error("Exception Occurred.", e);
            postResDto.setResultCode(ResultCode.BAD_DATA_REQUEST);
            return postResDto;
        } catch (DataAccessException e) {
            log.error("Exception Occurred.", e);
            postResDto.setResultCode(ResultCode.UNKNOWN_DATABASE_ERROR);
            return postResDto;
        } catch (Exception e) {
            log.error("Exception Occurred.", e);
            postResDto.setResultCode(ResultCode.UNKNOWN_ERROR);
            return postResDto;
        }

        if (updatedRows <= 0) {
            log.error("변경된 데이터가 없습니다.");
            postResDto.setResultCode(ResultCode.NO_UPDATED_ROWS);
            return postResDto;
        }

        postResDto.setResultCode(ResultCode.SUCCESSFUL);
        return postResDto;
    }

    @DeleteMapping("/post/{id}")
    public PostResDto delete(@PathVariable("id") int id) {
        PostResDto postResDto = new PostResDto();
        int updatedRows = -1;
        try {
            updatedRows = postService.delete(id);
        } catch (DataAccessException e) {
            log.error("Exception Occurred.", e);
            postResDto.setResultCode(ResultCode.UNKNOWN_DATABASE_ERROR);
            return postResDto;
        } catch (Exception e) {
            log.error("Exception Occurred.", e);
            postResDto.setResultCode(ResultCode.UNKNOWN_ERROR);
            return postResDto;
        }

        if (updatedRows <= 0) {
            log.error("변경된 데이터가 없습니다.");
            postResDto.setResultCode(ResultCode.NO_UPDATED_ROWS);
            return postResDto;
        }

        postResDto.setResultCode(ResultCode.SUCCESSFUL);
        return postResDto;
    }
}
