package com.purple.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookmarkEntity {
    private int bookmark_feedpk;    // 피드 번호
    private int bookmark_userpk;    // 회원 번호
}
