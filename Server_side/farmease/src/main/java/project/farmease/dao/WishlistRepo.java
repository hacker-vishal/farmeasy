package project.farmease.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import project.farmease.pojo.Wishlist;
import project.farmease.pojo.WishlistId;

public interface WishlistRepo extends JpaRepository<Wishlist, WishlistId> {

	List<Wishlist> findByEmail(String email);}