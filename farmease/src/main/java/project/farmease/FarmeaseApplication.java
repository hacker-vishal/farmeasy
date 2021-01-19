package project.farmease;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import project.farmease.controller.HomeAndSearch;
import project.farmease.poc.Bcrypt;
import project.farmease.poc.Poc;
import project.farmease.pojo.Hostuser;

@SpringBootApplication
public class FarmeaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(FarmeaseApplication.class, args);
		
//		Logger logger = LogManager.getLogger(FarmeaseApplication.class);
		
//		Poc poc = new Poc(); 
//		poc.log();
		
	    //logger.debug(1);
		
		//Bcrypt b = new Bcrypt();
		//b.bcryptwork();
		
	}	
}