package com.learncode.blog.services;

//Importing List packages from util
import java.util.List;
import com.learncode.blog.entities.User;
import com.learncode.blog.payloads.UserDto;

public interface UserService {
	
//	User createUser(User user);
	//Method to create the user
	UserDto createUser(UserDto user);
	
	UserDto updateUser(UserDto user,Integer userId);
	
	UserDto getUserById(Integer userId);
	
	List<UserDto> getAllUsers();
	
	void deleteUser(Integer userId);

}
