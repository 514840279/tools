package org.chuxue.application.config;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
	
	User getUserByUsername(@Param("username") String username);
	
	void updateLastLoginTime(@Param("username") String username);
	
}
