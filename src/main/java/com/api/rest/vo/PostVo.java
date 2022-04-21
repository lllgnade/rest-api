package com.api.rest.vo;

import lombok.Data;

@Data
public class PostVo {
    private Integer userId;
    private Integer id;
    private String title;
    private String body;
}
