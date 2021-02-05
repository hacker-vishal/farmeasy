package project.farmease.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import project.farmease.pojo.Booking;

public interface BookingRepo extends JpaRepository<Booking, Integer>{

	@Query("from Booking where serviceprovider=:serviceprovider and invalid=false")
	List<Booking> findByServiceprovider(@Param("serviceprovider") String serviceprovider);

	@Query("from Booking where email=:email and invalid=false")
	List<Booking> findByEmail(@Param("email") String username);
	
	@Procedure("Update_invalid")
	void updateinvalidmark();
}