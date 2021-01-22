package project.farmease.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import project.farmease.pojo.Address;
import project.farmease.pojo.AddressId;

@Repository
public interface AddressRepo extends JpaRepository<Address, AddressId>{
	
	@Modifying
	@Transactional
	@Query("update Address set location=:location, city=:city, state=:state, zipcode=:zipcode where email=:email")
	int updateforaddress(@Param("email") String email, @Param("location") String location, @Param("city") String city, 
			@Param("state") String state, @Param("zipcode") int zipcode);
	}
