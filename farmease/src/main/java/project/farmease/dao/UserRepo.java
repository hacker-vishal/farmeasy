package project.farmease.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import project.farmease.pojo.User;
import project.farmease.pojo.VerificationToken;

@Transactional
@Repository 
public interface UserRepo extends JpaRepository<User, String>{

	@Modifying
	@Query("update User set fname=:fname, lname=:lname, mobileno=:mobileno where email=:email")
	int updateforuser(@Param("email") String email, @Param("fname") String fname, @Param("lname") String lname, @Param
			("mobileno") String mobileno);
	
	@Modifying
	@Query("update User set otp=:random where email=:id")
	void createotp(@Param("random") int random, @Param("id") String id);
	
	Optional<User> findByOtp(int otp);
	
	@Modifying
	@Query("update User set password=:pswd, otp=null where email=:id")
	int resetpswd(@Param("id") String id, @Param("pswd") String pswd);
	}