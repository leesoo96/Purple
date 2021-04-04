package com.purple.demo.model.DTO;

import com.purple.demo.model.AlarmEntity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AlarmDTO extends AlarmEntity{
    private String user_id;
    private String user_profileimg;
}
