package project.farmease.poc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Bcrypt {
	
	Logger logger = LogManager.getLogger(Bcrypt.class);
	
	public void bcryptwork() {
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode("psagar");
		logger.debug(encodedPassword);
		
	}

}
