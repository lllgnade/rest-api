package com.api.rest.dto;

import com.api.rest.vo.PostVo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostListResDto {
    private Integer errorCode = null;
    private List<PostVo> postList;
}
