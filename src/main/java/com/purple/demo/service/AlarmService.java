package com.purple.demo.service;

import java.util.List;

import com.purple.demo.common.Utils;
import com.purple.demo.mapper.AlarmMapper;
import com.purple.demo.model.DTO.AlarmDTO;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlarmService {

    final Utils utils;

    final AlarmMapper alarmMapper;

    public List<AlarmDTO> getAlarm(int user_pk) {
        return alarmMapper.getAlarm(user_pk);
    }
    
    public int getAlarmCount(String user_id) {
        int user_pk = utils.getUserPkFromId(user_id);
        return alarmMapper.getAlarmCount(user_pk);
    }
    
    public int readAlarm(int user_pk) {
        return alarmMapper.readAlarm(user_pk);
    }
}
