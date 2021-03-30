package com.purple.demo.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CommonMapper {

	int getUserPkFromId(String user_id);
}
