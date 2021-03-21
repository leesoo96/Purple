package com.purple.demo.model.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedFavoriteDTO {
    private int favorite_count; //좋아요 수
    private int favorite_state;
    private int favorite_feedpk;
    private int favorite_userpk;
}
