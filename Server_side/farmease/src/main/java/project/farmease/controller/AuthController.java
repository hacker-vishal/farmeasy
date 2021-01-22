package project.farmease.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.*;

import project.farmease.dto.AuthenticationResponse;
import project.farmease.dto.RefreshTokenRequest;
import project.farmease.dto.RegisterRequest;
import project.farmease.dto.Userdto;
import project.farmease.service.AuthService;
import project.farmease.service.RefreshTokenService;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

//import jdk.internal.org.jline.utils.Log;

//import javax.validation.Valid;




//import static org.springframework.http.HttpStatus.OK;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/auth")            // authentication request should come in this format
public class AuthController {

	Logger logger = LogManager.getLogger(AuthController.class);
	
	@Autowired
	private AuthService authService;
	@Autowired
	private RefreshTokenService refreshTokenService;
	
	@CrossOrigin("http://localhost:4200") 
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
		    logger.info("1. signup process started");
		 authService.signup(registerRequest);
		    logger.info("signup process completed");
		 return new ResponseEntity<>("User Registration Successful", HttpStatus.OK);
		
    }

	
//	 @GetMapping("accountVerification/{token}")     
//	 public ResponseEntity<String> verifyAccount(@PathVariable String token) 
//	 {
//		    logger.info("Account verification started with token {}: ",token);
//		 authService.verifyAccount(token);
//	     return new ResponseEntity<>("Account Activated Successfully", HttpStatus.OK);
//	 }
	 
	@PostMapping("/login") 
	public AuthenticationResponse login(@RequestBody Userdto userdto) {
		    logger.info("Login request started for user {}: ",userdto.getUsername());
		return authService.login(userdto);
		}
	 
	 @PostMapping("/refresh/token")
	 public AuthenticationResponse refreshTokens(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
	        logger.info("generate refersh token process started");     
		  return authService.refreshToken(refreshTokenRequest);
	           }
	 
	  @PostMapping("/logout")
	  public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
		  refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
		     logger.info("logout happens and refresh token deleted");
		  return ResponseEntity.status(HttpStatus.OK).body("Refresh Token Deleted Successfully!!"); }
	 }
