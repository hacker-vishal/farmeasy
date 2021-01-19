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


@Repository 
public interface UserRepo extends JpaRepository<User, String>{

	@Modifying
	@Transactional
	@Query("update User set fname=:fname, lname=:lname, mobileno=:mobileno where email=:email")
	int updateforuser(@Param("email") String email, @Param("fname") String fname, @Param("lname") String lname, @Param
			("mobileno") String mobileno);

	Optional<User> findByEmail(String username);

	boolean existsByEmail(String email);
	}