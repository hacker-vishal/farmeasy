package project.farmease.security;

import static io.jsonwebtoken.Jwts.parser;
import static java.util.Date.from;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.sql.Date;
import java.time.Instant;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import project.farmease.farmeasyexception.FarmeasyException;

@Service
public class JwtProvider {

	private KeyStore keyStore;
    @Value("${jwt.expiration.time}")
    private Long jwtExpirationInMillis;
    
    Logger logger = LogManager.getLogger(JwtProvider.class);
    
	
    // ---------------------------- JWT signing code ----------------------------------- 

    @PostConstruct
    public void init() {          // We are using AsymmetricEncryption to sign our JWT’s using Java Keystore (using Public-Private Key)
        try {
            keyStore = KeyStore.getInstance("JKS");
            InputStream resourceAsStream = getClass().getResourceAsStream("/farmeasy.jks");
            keyStore.load(resourceAsStream, "farmeasy".toCharArray());
            logger.info("signing our JWT’s using farmeasy.jks Keystore");
        } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
            throw new FarmeasyException("Exception occurred while loading keystore",e);
        }

    }
    
	// ---------------------------- Generating token code ----------------------------------- 

    public String generateToken(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        logger.info("4. token generated and send to login method ");
        
        return Jwts.builder().setSubject(principal.getUsername()).setIssuedAt(from(Instant.now())).signWith(getPrivateKey()).setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis))).compact();
             
    }

    public String generateTokenWithUserName(String username) {
    	logger.info("Generating token with username which will expire after 300 sec");
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(from(Instant.now()))
                .signWith(getPrivateKey())
                .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis)))
                .compact();
    }

    private PrivateKey getPrivateKey() {
        try {
        	return (PrivateKey) keyStore.getKey("farmeasy","farmeasy".toCharArray());
        	    //PrivateKey privatekey=(PrivateKey) keyStore.getKey("farmeasy","farmeasy".toCharArray());
        	    //logger.debug(privatekey);
            //return privatekey;// here "farmeasy" is the alias, and "farmeasy" is the password for this alias.
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
            throw new FarmeasyException("Exception occured while retrieving public key from keystore",e);
        }
    }

    public boolean validateToken(String jwt) {
    	logger.info("token validation happens in jwtProvider class");
        parser().setSigningKey(getPublickey()).parseClaimsJws(jwt);
        return true;
    }

    private PublicKey getPublickey() {
        try {
        	logger.info("getPublicKey() called and getting certificate from keystore farmeasy ");
            return keyStore.getCertificate("farmeasy").getPublicKey();
        } catch (KeyStoreException e) {
            throw new FarmeasyException("Exception occured while " +
                    "retrieving public key from keystore");
        }
    }

    
	// ---------------------------- Extracting username from jwt code ----------------------------------- 
    
    public String getUsernameFromJwt(String token) {        // we have to extract username from JWt because we use it as a subject while creating the JWT token
    	
    	logger.info("extracting username from jwt ");
        Claims claims = parser()
                .setSigningKey(getPublickey())
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public Long getJwtExpirationInMillis() {
        return jwtExpirationInMillis;
    }
}
