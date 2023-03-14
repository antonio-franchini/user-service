package com.userservice.app.io.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import com.userservice.app.io.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public
class UserRepositoryTest {

	@Autowired
	UserRepository userRepository;
	
	static boolean recordsCreated = false;
	

	@BeforeEach
	void setUp() throws Exception {
		
		if(!recordsCreated) createRecrods();
	}

	private void createRecrods()
	{
		// Prepare User Entity
	     UserEntity userEntity = new UserEntity();
	     userEntity.setFirstName("Antonio");
	     userEntity.setLastName("Franchini");
	     userEntity.setUserId("1a2b3c");
	     userEntity.setEncryptedPassword("xxx");
	     userEntity.setEmail("test@test.com");
	     userRepository.save(userEntity);

		 // Prepare User Entity
	     UserEntity userEntity2 = new UserEntity();
	     userEntity2.setFirstName("Antonio");
	     userEntity2.setLastName("Franchini");
	     userEntity2.setUserId("1a2b3cddddd");
	     userEntity2.setEncryptedPassword("xxx");
	     userEntity2.setEmail("test@test.com");
	     userRepository.save(userEntity2);
	     
	     recordsCreated = true;
    
	}

}
