package com.api.rest.service;

import com.api.rest.mapper.PostMapper;
import com.api.rest.vo.PostVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PostService {

    @Autowired
    PostMapper postMapper;

    public void create(PostVo postVo) {
        postMapper.create(postVo);
    }

    public List<PostVo> selectAll() {
        return postMapper.selectAll();
    }

    public PostVo selectById(int id) {
        return postMapper.selectById(id);
    }

    public List<PostVo> selectByQuery(PostVo postVo) {
        return postMapper.selectByQuery(postVo);
    }

    public int update(PostVo postVo) {
        return postMapper.update(postVo);
    }

    public int delete(int id) {
        return postMapper.delete(id);
    }
}
