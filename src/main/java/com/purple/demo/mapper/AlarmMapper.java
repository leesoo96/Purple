package com.purple.demo.mapper;

import java.util.List;

import com.purple.demo.model.DTO.AlarmDTO;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface AlarmMapper {
    List<AlarmDTO> getAlarm(int user_pk);
}
