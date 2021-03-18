package com.purple.demo.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedWriteDTO extends FeedEntity{
    private List hashtag;
}
