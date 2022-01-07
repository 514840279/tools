package org.chuxue.application;

import org.chuxue.application.dao.UserDetailsRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;

@SpringBootApplication
public class Application {
	
	public static void main(String args[]) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public UserDetailsRepository userDetailsRepository() {
		UserDetailsRepository userDetailsRepository = new UserDetailsRepository();
		// 为了让我们的登录能够运行 这里我们初始化一个用户Felordcn 密码采用明文 当你在密码 12345上使用了前缀{noop} 意味着你的密码不使用加密，authorities 一定不能为空 这代表用户的角色权 限集合
		UserDetails felordcn = User.withUsername("aaa").password("{noop}12345").authorities(AuthorityUtils.NO_AUTHORITIES).build();
		userDetailsRepository.createUser(felordcn);
		return userDetailsRepository;
	}
	
	@Bean
	public UserDetailsManager userDetailsManager(UserDetailsRepository userDetailsRepository) {
		return new UserDetailsManager() {
			@Override
			public void createUser(UserDetails user) {
				userDetailsRepository.createUser(user);
			}
			
			@Override
			public void updateUser(UserDetails user) {
				userDetailsRepository.updateUser(user);
			}
			
			@Override
			public void deleteUser(String username) {
				userDetailsRepository.deleteUser(username);
			}
			
			@Override
			public void changePassword(String oldPassword, String newPassword) {
				userDetailsRepository.changePassword(oldPassword, newPassword);
			}
			
			@Override
			public boolean userExists(String username) {
				return userDetailsRepository.userExists(username);
			}
			
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				return userDetailsRepository.loadUserByUsername(username);
			}
		};
	}
	
}
