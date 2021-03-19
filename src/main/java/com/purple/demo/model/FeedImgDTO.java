package com.purple.demo.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedImgDTO extends MediaEntity{
    private List<MultipartFile> imgs;
}
