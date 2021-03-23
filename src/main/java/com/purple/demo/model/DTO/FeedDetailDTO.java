package com.purple.demo.model.DTO;

import java.util.List;

import com.purple.demo.model.FeedListDTO;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FeedDetailDTO extends FeedListDTO{
    private List<CommentListDTO> comment_list;
}
