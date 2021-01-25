package project.farmease.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import project.farmease.pojo.Hostuser;
import project.farmease.pojo.HostuserId;

public interface HostuserRepo extends JpaRepository<Hostuser, HostuserId> {

	@Query("from Hostuser where equipmenttype=:equipment and location=:location")
	List<Hostuser> findmatchingservice(@Param("equipment") String equipment, @Param("location") String location);

	int existsByHostemail(String hostemail);
	}