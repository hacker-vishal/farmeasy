package project.farmease.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import project.farmease.models.Booking;

public interface BookingRepo extends JpaRepository<Booking, Integer>{}