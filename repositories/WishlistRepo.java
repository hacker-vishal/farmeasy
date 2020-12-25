package project.farmease.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import project.farmease.models.Wishlist;
import project.farmease.models.WishlistId;

public interface WishlistRepo extends JpaRepository<Wishlist, WishlistId> {}