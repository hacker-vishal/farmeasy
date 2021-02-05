package project.farmease.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.farmease.dao.UserRepo;
import project.farmease.dao.VerificationTokenRepo;
import project.farmease.dto.AuthenticationResponse;
import project.farmease.dto.RefreshTokenRequest;
import project.farmease.dto.RegisterRequest;
import project.farmease.dto.Userdto;
import project.farmease.farmeasyexception.FarmeasyException;
import project.farmease.pojo.User;
import project.farmease.pojo.VerificationToken;
import project.farmease.security.JwtProvider;


@Service
public class AuthService {
	
	Logger logger = LogManager.getLogger(AuthService.class);
	
	@Autowired
	 private PasswordEncoder passwordEncoder;
	@Autowired
	 private UserRepo userRepo; 
	@Autowired
	 private VerificationTokenRepo verificationTokenRepo; 
	@Autowired
	 private AuthenticationManager authenticationManager; 
	@Autowired
	 private JwtProvider jwtProvider;
	@Autowired
	 private RefreshTokenService refreshTokenService;
	 
	// ---------------------------- Sign up Service code ----------------------------------- 

	@Transactional                 // transactional because it interconnects with RDBMS
	public void signup(RegisterRequest registerRequest) {
		    logger.debug("hi you are getting it wrong!");
        User user = new User();
            logger.info("2. Registration request got");
        user.setFname(registerRequest.getFname());
            logger.info("3. Fname {} ",registerRequest.getFname());
        user.setLname(registerRequest.getLname());
            logger.info("4. Lname {} ",registerRequest.getLname());
        user.setEmail(registerRequest.getEmail());
            logger.info("5. Email {} ",registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));     // here we are encoding the password
            logger.info("6. Encoded Password {} ",registerRequest.getPassword());
        user.setMobileno(registerRequest.getMobileno());
            logger.info("7. Mobileno {} ",registerRequest.getMobileno());
        user.setCreated(Instant.now());
        user.setEnabled(true);  //set it to false and it will be set to true only when user verifies his/her email
        
		userRepo.save(user);
		    logger.info("8. User credential Saved in Database");
		 
		 String token = generateVerificationToken(user);    
		 //it will be sent to email of user and verified by him through verification link

}


	// ---------------------------- Generating verification token code ----------------------------------- 
  
  private String generateVerificationToken(User user) {  
	  String token = UUID.randomUUID().toString(); 
	  VerificationToken verificationToken = new VerificationToken();
	  verificationToken.setToken(token);
      verificationToken.setUser(user);
            logger.info("9. verification token generated and it is a random token, token {} ",token);
       verificationTokenRepo.save(verificationToken);      // these token will stored in DB , so that user can verify its account anytime
         return token;
      }
  
	
  // ---------------------------- verify token code ----------------------------------- 
 
   public void verifyAccount(String token) {     // here we query the verificationTokenRepository by the given token   
	      Optional<VerificationToken> verificationToken = verificationTokenRepo.findByToken(token);   // this will check the token present in DB(token) or not
             logger.info("verifyAccount() method called");
	      fetchUserAndEnable(verificationToken.orElseThrow(() -> new FarmeasyException("Invalid Token"))); // if token not found, it will throw the custom exception "invalid token" or if found , it will enable the user
                
   }
   
	
   // ---------------------------- Fetching user and enabling it code ----------------------------------- 
   
   @Transactional  
   private void fetchUserAndEnable(VerificationToken verificationToken) { // it will take the verificationToken and find by username, if found it will enable the user else throw exception... 
  	 String username = verificationToken.getUser().getEmail();
       User user = userRepo.findById(username).orElseThrow(() -> new  FarmeasyException("User not found with name - " + username));
           user.setEnabled(true); 
           userRepo.save(user);
           }
  
   
	
   // ---------------------------- Login Service code ----------------------------------- 
   
  public AuthenticationResponse login(Userdto userdto) {
	       logger.debug(userdto.getPassword());
           Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userdto.getUsername(),userdto.getPassword()));
              logger.info("2. we get username : {} and password : {} ",userdto.getUsername(),userdto.getPassword() );
           
           SecurityContextHolder.getContext().setAuthentication(authenticate);  // we are checking whether user is logged in or not
              logger.info("3. Authenticated object {} :", authenticate);
            
           String token = jwtProvider.generateToken(authenticate);
          
              logger.info("5. token generated {} and return to AuthResponse", token);
              logger.info("6. refresh token passed as empty string so if we performed login , we get one Json web token , one refresh token and expiry date" );
           
           return AuthenticationResponse.builder().authenticationToken(token).refreshToken(refreshTokenService.generateRefreshToken().getToken()).expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis())).username(userdto.getUsername()).build(); 
           }
  
	
  // ---------------------------- Refresh token Service code ----------------------------------- 
  
  public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
       refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
       
       String token = jwtProvider.generateTokenWithUserName(refreshTokenRequest.getUsername());
            logger.info("here we validate refreshToken, if success , this will generate new refreshToken with username{}: ",token);
       return AuthenticationResponse.builder().authenticationToken(token).refreshToken(refreshTokenRequest.getRefreshToken()).expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis())).username(refreshTokenRequest.getUsername()).build(); 
       }
 
  
	// ---------------------------- Saving user in Securitycontextholder code ----------------------------------- 
  
         public boolean isLoggedIn() {
        	 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        	 return  !(authentication instanceof AnonymousAuthenticationToken) &&  authentication.isAuthenticated(); 
        	 }
}
