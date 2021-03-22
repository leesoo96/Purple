package com.purple.demo.model.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedBookmarkDTO {
   private int bookmark_state;   // 북마크 상태
   private int bookmark_feedpk;  // 피드 번호
   private int bookmark_userpk;  // 회원 번호
}
