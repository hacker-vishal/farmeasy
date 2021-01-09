package project.farmease.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import project.farmease.models.Hostuser;
import project.farmease.models.HostuserId;

public interface HostuserRepo extends JpaRepository<Hostuser, HostuserId> {}