package com.api.rest.mapper;

import com.api.rest.vo.PostVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {

    int create(PostVo postVo);

    List<PostVo> selectAll();

    PostVo selectById(int id);

    int update(PostVo postVo);

    int delete(int id);

    //selectByUserId, ByTitle 등
}
