package project.farmease.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import project.farmease.pojo.RefreshToken;
import java.util.Optional;

public interface RefreshTokenRepo extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    void deleteByToken(String token);
}
