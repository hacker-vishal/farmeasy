package project.farmease.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.farmease.dao.RefreshTokenRepo;
import project.farmease.farmeasyexception.FarmeasyException;
import project.farmease.pojo.RefreshToken;

import java.time.Instant;
import java.util.UUID;

@Service
@Transactional
public class RefreshTokenService {         //this class is responsible to create, delete and validate refresh token

	@Autowired
    private RefreshTokenRepo refreshTokenRepo;
    Logger logger = LogManager.getLogger(RefreshToken.class);

    public RefreshToken generateRefreshToken() {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setCreatedDate(Instant.now());

           logger.info("refresh token stored in database {}:", refreshToken);
        return refreshTokenRepo.save(refreshToken);             // refresh token stored in database
    }

    void validateRefreshToken(String token) {
    	   logger.info("validating refresh token {} :");
        refreshTokenRepo.findByToken(token)
                .orElseThrow(() -> new FarmeasyException("Invalid refresh Token"));
    }

    public void deleteRefreshToken(String token) {
    	   logger.info("Deleting Refresh token {}: ",token);
        refreshTokenRepo.deleteByToken(token);
    }
}
