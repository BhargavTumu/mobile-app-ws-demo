package com.btumu.app.ws.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btumu.app.ws.io.entity.UserEntity;
import com.btumu.app.ws.repository.UserRepository;
import com.btumu.app.ws.service.UserService;
import com.btumu.app.ws.shared.Utils;
import com.btumu.app.ws.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Utils utils;

	@Override
	public UserDto createUser(UserDto userDto) {
		
		if(userRepository.findByEmail(userDto.getEmail()) != null) {
			
			throw new RuntimeException("Record already exists");
		}
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(userDto, userEntity);
		
		String userId = utils.generateUserId(30);
		
		userEntity.setUserId(userId);
		userEntity.setEncryptedPassword("testUserEncryptedPass");
		
		UserEntity storedUser = userRepository.save(userEntity);
		
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(storedUser, returnValue);
		
		return returnValue;
	}

}
