package com.userservice.app.io.repository;

import java.util.List;

import com.userservice.app.io.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
	UserEntity findByEmail(String email);
	UserEntity findByUserId(String userId);
	
//	@Query(value="select * from Users u where u.first_name = ?1",nativeQuery=true)
//	List<UserEntity> findUserByFirstName(String firstName);

}
