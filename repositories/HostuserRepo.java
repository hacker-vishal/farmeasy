package project.farmease.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import project.farmease.models.Hostuser;
import project.farmease.models.HostuserId;

public interface HostuserRepo extends JpaRepository<Hostuser, HostuserId> {

	@Query("from Hostuser where equipmenttype=:equipment and location=:location")
	List<Hostuser> findmatchingservice(@Param("equipment") String equipment, @Param("location") String location);
	}