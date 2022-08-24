package org.chuxue.application.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.chuxue.application.po.User;

@Mapper
public interface UserMapper {
	
	User getUserByUsername(@Param("username") String username);
	
	void updateLastLoginTime(@Param("username") String username);
	
}
