package com.api.rest.mapper;

import com.api.rest.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<UserVo> allUsers();
}
