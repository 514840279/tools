package org.chuxue.application.config;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@TableName("sys_user")
public class User implements UserDetails {

	private static final long					serialVersionUID	= 1L;
	
	@TableId
	private String								username;

	private String								password;
	
	private Boolean								isAccountNonExpired;
	private Boolean								isAccountNonLocked;
	private Boolean								isCredentialsNonExpired;
	private Boolean								isEnabled;
	
	private List<? extends GrantedAuthority>	authorities;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}
	
	@Override
	public String getPassword() {
		return this.password;
	}
	
	@Override
	public String getUsername() {
		return this.username;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return this.isAccountNonExpired == null ? true : this.isAccountNonExpired;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return this.isAccountNonLocked == null ? true : this.isAccountNonLocked;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return this.isCredentialsNonExpired == null ? true : this.isCredentialsNonExpired;
	}
	
	@Override
	public boolean isEnabled() {
		return this.isEnabled == null ? true : this.isEnabled;
	}

}