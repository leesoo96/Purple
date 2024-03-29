package com.purple.demo.controller;

import java.util.List;

import com.purple.demo.model.DTO.AlarmDTO;
import com.purple.demo.service.AlarmService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/alarm")
@RequiredArgsConstructor
public class AlarmController {

	final AlarmService alarmService;

    @RequestMapping(value = "")
	public String alarm(){
		return "/alarm";
	}

	@ResponseBody
    @GetMapping("/getalarm/{user_pk}")
    public List<AlarmDTO> getAlarm(@PathVariable int user_pk) {
        return alarmService.getAlarm(user_pk);
    }

    @ResponseBody
    @GetMapping("/getalarmcount/{user_id}")
    public int getAlarmCount(@PathVariable String user_id) {
        return alarmService.getAlarmCount(user_id);
    }

    @ResponseBody
    @GetMapping("/readalarm/{user_pk}")
    public int readAlarm(@PathVariable int user_pk) {
        return alarmService.readAlarm(user_pk);
    }
}
