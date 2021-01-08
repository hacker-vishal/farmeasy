package project.farmease.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.farmease.models.User;


@Repository 
public interface UserRepo extends JpaRepository<User, String>{}