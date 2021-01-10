package project.farmease.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import project.farmease.models.Logincreds;

public interface LogincredsRepo extends JpaRepository<Logincreds, String> {
	@Modifying
	@Transactional
	@Query("update Logincreds set otp=:random where email=:id")
	void createotp(@Param("random") int random, @Param("id") String id);

	@Query("from Logincreds where otp=:otp")
	Optional<Logincreds> checkotp(@Param("otp") int otp);

	@Modifying
	@Transactional
	@Query("update Logincreds set password=:pswd, otp=:null where email=:id")
	void resetpswd(@Param("id") String id, @Param("pswd") String pswd);
}