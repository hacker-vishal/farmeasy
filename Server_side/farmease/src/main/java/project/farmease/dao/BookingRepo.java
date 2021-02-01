package project.farmease.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import project.farmease.pojo.Booking;
import project.farmease.pojo.Wishlist;

public interface BookingRepo extends JpaRepository<Booking, Integer>{

	@Query("from Booking where serviceprovider=:serviceprovider and isInvalid=false")
	List<Booking> findByServiceprovider(@Param("serviceprovider") String serviceprovider);

	@Query("from Booking where email=:email and isInvalid=false")
	List<Booking> findByEmail(@Param("email") String username);
}