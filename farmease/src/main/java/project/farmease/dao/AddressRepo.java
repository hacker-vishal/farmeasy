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
	}
