package project.farmease.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.farmease.models.Address;
import project.farmease.models.AddressId;

@Repository
public interface AddressRepo extends JpaRepository<Address, AddressId>{}
