package com.purple.demo.service;

import java.util.List;

import com.purple.demo.mapper.AlarmMapper;
import com.purple.demo.model.DTO.AlarmDTO;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlarmService {

    final AlarmMapper alarmMapper;

    public List<AlarmDTO> getAlarm(int user_pk) {
        return alarmMapper.getAlarm(user_pk);
    }
    
}
