package project.farmease.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import project.farmease.pojo.Booking;

public interface BookingRepo extends JpaRepository<Booking, Integer>{}