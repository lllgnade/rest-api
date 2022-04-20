package com.api.rest.dto;

import com.api.rest.vo.PostVo;
import lombok.Data;

import java.util.List;

@Data
public class PostReqDto {
    List<PostVo> postList;
    private PostVo post;
}
