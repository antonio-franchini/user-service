package com.userservice.app.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.userservice.app.shared.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.userservice.app.io.entity.UserEntity;
import com.userservice.app.io.repository.UserRepository;
import com.userservice.app.shared.Utils;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImplTest {

	@InjectMocks
	UserServiceImpl userService;

	@Mock
	UserRepository userRepository;
 
	@Mock
	Utils utils;

	@Mock
	BCryptPasswordEncoder bCryptPasswordEncoder;
 
	String userId = "hhty57ehfy";
	String encryptedPassword = "74hghd8474jf";
	
	UserEntity userEntity;


	@BeforeEach
	void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		
		userEntity = new UserEntity();
		userEntity.setId(1L);
		userEntity.setFirstName("Antonio");
		userEntity.setLastName("Franchini");
		userEntity.setUserId(userId);
		userEntity.setEncryptedPassword(encryptedPassword);
		userEntity.setEmail("test@test.com");
	}

	@Test
	final void testGetUser()
	{
		when(userRepository.findByEmail(anyString())).thenReturn(userEntity);
		UserDto userDto = userService.getUser("test@test.com");

		assertNotNull(userDto);
		assertEquals("Antonio", userDto.getFirstName());

	}

	@Test
	final void testGetUsers()
	{
		List<UserEntity> userEntities = new ArrayList<>();
		userEntities.add(userEntity);
		userEntities.add(userEntity);
		userEntities.add(userEntity);
		when(userRepository.findAll()).thenReturn(userEntities);

		List<UserDto> users = userService.getUsers();
		users.forEach(user -> assertEquals("Antonio", user.getFirstName()));
	}

	@Test
	final void testGetUserByUsername()
	{
		when(userRepository.findByEmail(anyString())).thenReturn(userEntity);
		UserDetails userDetails = userService.loadUserByUsername("test@test.com");

		assertNotNull(userDetails);
		assertEquals(encryptedPassword, userDetails.getPassword());

	}

	@Test
	final void testGetUserByUserId()
	{
		when(userRepository.findByUserId(anyString())).thenReturn(userEntity);
		UserDto userDto = userService.getUserByUserId(userId);

		assertNotNull(userDto);
		assertEquals("Antonio", userDto.getFirstName());

	}

	@Test
	final void testGetUser_UsernameNotFoundException()
	{
		when(userRepository.findByEmail(anyString())).thenReturn(null);
		assertThrows(UsernameNotFoundException.class,
				() -> {
					userService.getUser("test@test.com");
				}
		);
	}
	
	@Test
	final void testCreateUser()
	{
		when(userRepository.findByEmail(anyString())).thenReturn(null);
		when(utils.generateUserId(anyInt())).thenReturn("hgfnghtyrir884");
		when(utils.generateUserId(anyInt())).thenReturn(userId);
		when(bCryptPasswordEncoder.encode(anyString())).thenReturn(encryptedPassword);
		when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

		UserDto userDto = new UserDto();
		userDto.setFirstName("Antonio");
		userDto.setLastName("Franchini");
		userDto.setPassword("12345678");
		userDto.setEmail("test@test.com");

		UserDto storedUserDetails = userService.createUser(userDto);
		assertNotNull(storedUserDetails);
		assertEquals(userEntity.getFirstName(), storedUserDetails.getFirstName());
		assertEquals(userEntity.getLastName(), storedUserDetails.getLastName());
		assertNotNull(storedUserDetails.getUserId());
		verify(bCryptPasswordEncoder, times(1)).encode("12345678");
		verify(userRepository,times(1)).save(any(UserEntity.class));
	}

	@Test
	final void testUpdateUser()
	{
		when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
		when(userRepository.findByUserId(anyString())).thenReturn(userEntity);

		UserDto userDto = new UserDto();
		userDto.setFirstName("Antonio");
		userDto.setLastName("Franchini");
		userDto.setPassword("12345678");
		userDto.setEmail("test@test.com");

		UserDto storedUserDetails = userService.updateUser(userId, userDto);
		assertNotNull(storedUserDetails);
		assertEquals(userEntity.getFirstName(), storedUserDetails.getFirstName());
		assertEquals(userEntity.getLastName(), storedUserDetails.getLastName());
		assertNotNull(storedUserDetails.getUserId());
		verify(userRepository,times(1)).save(any(UserEntity.class));
	}

	@Test
	final void testUpdateUser_IllegalArgumentException()
	{
		when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
		when(userRepository.findByUserId(anyString())).thenReturn(null);

		UserDto userDto = new UserDto();
		userDto.setFirstName("Antonio");
		userDto.setLastName("Franchini");
		userDto.setPassword("12345678");
		userDto.setEmail("test@test.com");

		assertThrows(IllegalArgumentException.class,
				() -> {
					userService.updateUser(userId, userDto);
				}
		);
	}

	@Test
	final void testDeleteUser()
	{
		when(userRepository.findByUserId(anyString())).thenReturn(userEntity);
		userService.deleteUser(userId);
		verify(userRepository,times(1)).delete(any(UserEntity.class));
	}

	@Test
	final void testDeleteUser_IllegalArgumentException()
	{
		when(userRepository.findByUserId(anyString())).thenReturn(null);
		assertThrows(IllegalArgumentException.class,
				() -> {
					userService.deleteUser(userId);
				}
		);
	}
}
