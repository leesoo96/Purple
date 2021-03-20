package com.purple.demo.model.DTO;

import java.util.List;

import com.purple.demo.model.FeedEntity;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FeedDetailDTO extends FeedEntity{
    private String user_id;
    private String user_profileimg;
    private int comment_count;
    private List media_url;
    private List comment;
    private List hastag;
}
