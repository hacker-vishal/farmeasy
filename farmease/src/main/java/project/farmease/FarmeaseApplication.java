package project.farmease;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import project.farmease.poc.Bcrypt;
import project.farmease.poc.Poc;

@SpringBootApplication
@EnableAsync
public class FarmeaseApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(FarmeaseApplication.class, args);
		
//		Logger logger = LogManager.getLogger(FarmeaseApplication.class);
		
//		Poc poc = new Poc(); 
//		poc.log();
		
//	    logger.debug(1);
		
//		Bcrypt b = new Bcrypt();
//		b.bcryptwork();
		
	}	
}