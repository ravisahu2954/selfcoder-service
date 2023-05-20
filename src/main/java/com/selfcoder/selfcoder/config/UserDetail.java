package com.selfcoder.selfcoder.config;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.selfcoder.selfcoder.entity.User;

public class UserDetail implements UserDetails{

	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;
	private List<GrantedAuthority> list;
	
	public UserDetail(User user)
	{
		this.userName = user.getUserName();
		this.password = user.getPassword();
	    this.list = user.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
		
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return list;
	
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
        return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
