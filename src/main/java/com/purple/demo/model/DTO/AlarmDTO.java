package com.purple.demo.model.DTO;

import com.purple.demo.model.AlarmEntity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AlarmDTO extends AlarmEntity{
    // private int alarm_category;
    // private int alarm_sendto;
    // private int alarm_from;
    // private int alarm_valuepk;
    // private int alarm_readstate;
    private String user_id;
    private String user_profileimg;
}
