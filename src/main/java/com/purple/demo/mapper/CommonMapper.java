package com.purple.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommonMapper {

	int getUserPkFromId(String user_id);
}
