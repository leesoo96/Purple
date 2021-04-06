package com.purple.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlarmEntity {
    private int alarm_category;
    private int alarm_sendto;
    private int alarm_from;
    private int alarm_valuepk;
    private int alarm_readstate;
    private String alarm_date;
}
