package com.api.rest.dto;

import com.api.rest.vo.PostVo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostResDto {
    private ResultCode resultCode = null;
    private PostVo post;
}
