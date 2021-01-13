package project.farmease.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import project.farmease.models.Logincreds;

public interface LogincredsRepo extends JpaRepository<Logincreds, String> {}