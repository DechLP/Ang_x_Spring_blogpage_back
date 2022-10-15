package com.programming.dech.springngblog.dto;

import lombok.Data;

@Data
public class PostDto {

    private Long id;

    private String content;

    private String title;

    private String username;

}
