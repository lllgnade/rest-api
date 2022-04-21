package com.api.rest.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)       // 값이 비어있는 필드는 제외하고 JSON 매핑
// responseDto엔 붙여주는 게 좋다.
public class UserVo {
    private Integer id;
    private String name;
    private String username;
    private String email;
    private String password;
    private String address;
    private String phone;
    private String website;
    private String company;
}
