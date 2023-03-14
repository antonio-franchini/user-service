package com.userservice.app.ui.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import static org.mockito.ArgumentMatchers.*;

import com.userservice.app.ui.contoller.RequestOperationName;
import com.userservice.app.ui.model.request.UserDetailsRequestModel;
import com.userservice.app.ui.model.response.OperationStatusModel;
import com.userservice.app.ui.model.response.RequestOperationStatus;
import com.userservice.app.service.impl.UserServiceImpl;
import com.userservice.app.shared.dto.UserDto;
import com.userservice.app.ui.model.response.UserRest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.userservice.app.ui.contoller.UserController;

public class UserControllerTest {

	@InjectMocks
	UserController userController;
	
	@Mock
    UserServiceImpl userService;
	
	UserDto userDto;
	
	final String USER_ID = "bfhry47fhdjd7463gdh";
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		userDto = new UserDto();
        userDto.setFirstName("Antonio");
        userDto.setLastName("Franchini");
        userDto.setEmail("test@test.com");
        userDto.setUserId(USER_ID);
        userDto.setEncryptedPassword("xcf58tugh47");
		
	}

	@Test
	final void testGetUser() {
	    when(userService.getUserByUserId(anyString())).thenReturn(userDto);	
	    
	    UserRest userRest = userController.getUser(USER_ID);
	    
	    assertNotNull(userRest);
	    assertEquals(USER_ID, userRest.getUserId());
	    assertEquals(userDto.getFirstName(), userRest.getFirstName());
	    assertEquals(userDto.getLastName(), userRest.getLastName());
	}

	@Test
	void testCreateUser() throws Exception {
		when(userService.createUser(anyObject())).thenReturn(userDto);

		UserDetailsRequestModel newUser = new UserDetailsRequestModel();
		newUser.setFirstName("Antonio");
		newUser.setLastName("Franchini");
		newUser.setPassword("12345678");
		newUser.setEmail("test@test.com");

		UserRest userRest = userController.createUser(newUser);

		assertNotNull(userRest);

		assertEquals(userDto.getFirstName(), userRest.getFirstName());
		assertEquals(userDto.getLastName(), userRest.getLastName());
		assertEquals(userDto.getEmail(), userRest.getEmail());
	}

	@Test
	void testUpdateUser()throws Exception {
		when(userService.updateUser(anyString(), anyObject())).thenReturn(userDto);

		UserDetailsRequestModel updatedUser = new UserDetailsRequestModel();
		updatedUser.setFirstName("Mario");
		updatedUser.setLastName("Franchini");
		updatedUser.setPassword("12345678");
		updatedUser.setEmail("test@test.com");

		UserRest userRest = userController.updateUser(USER_ID, updatedUser);

		assertNotNull(userRest);

		assertEquals(userDto.getFirstName(), userRest.getFirstName());
		assertEquals(userDto.getLastName(), userRest.getLastName());
		assertEquals(userDto.getEmail(), userRest.getEmail());
	}

	@Test
	void testDeleteUser() {
		OperationStatusModel returnValue = new OperationStatusModel();
		returnValue.setOperationName(RequestOperationName.DELETE.name());
		returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());

		OperationStatusModel actualReturnValue = userController.deleteUser(USER_ID);

		assertNotNull(actualReturnValue);

		assertEquals(returnValue.getOperationName(), actualReturnValue.getOperationName());
		assertEquals(returnValue.getOperationResult(), actualReturnValue.getOperationResult());
	}

}
