package com.purple.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavoriteEntity {
    private int favorite_feedpk;    // 피드 번호
    private int favorite_userpk;    // 사용자 번호
}
