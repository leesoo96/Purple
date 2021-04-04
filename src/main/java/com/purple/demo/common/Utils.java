package com.purple.demo.common;

import com.purple.demo.mapper.CommonMapper;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Utils {

    final CommonMapper commonMapper;

    public int getUserPkFromId(String user_id) {
        return commonMapper.getUserPkFromId(user_id);
    }

    public String getUserIdFromPk(int user_pk) {
        return commonMapper.getUserIdFromPk(user_pk);
    }

    public boolean CheckNumber(String str){
		char check;
		if(str.equals(""))
		{
			return false;
		}
		
		for(int i = 0; i<str.length(); i++){
			check = str.charAt(i);
			if( check < 48 || check > 58)
			{
				return false;
			}
		}		
		return true;
	}
}
