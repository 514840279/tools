package org.chuxue.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 用户插叙
		User user = userMapper.getUserByUsername(username);
		// TOD权限管理
		return user == null ? new User() : user;
	}
	
	public void updateLastLoginTime(UserDetails sysUser) {
		userMapper.updateLastLoginTime(sysUser.getUsername());
		
	}
}
