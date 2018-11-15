package com.btumu.app.ws.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.btumu.app.ws.shared.dto.UserDto;

public interface UserService extends UserDetailsService{
	
	public UserDto createUser(UserDto userDto);
	public UserDto getUser(String email);

}
