package project.farmease.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import project.farmease.pojo.Hostuser;
import project.farmease.pojo.HostuserId;
import project.farmease.pojo.User;

public interface HostuserRepo extends JpaRepository<Hostuser, HostuserId> {

	@Query("from hostuser where equipmenttype=:equipment and location=:location")
	List<Hostuser> findmatchingservice(@Param("equipment") String equipment, @Param("location") String location);

	int existsByHostemail(String hostemail);
	
	
	
	}