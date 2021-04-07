package com.purple.demo.model.DTO;

import com.purple.demo.model.CommentEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentListDTO extends CommentEntity{
    private String user_id;
    private String user_profileimg;
}
