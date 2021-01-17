package project.farmease.poc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import project.farmease.dao.UserRepo;

@RestController
public class Poc {

	//logger test poc and connection tested here
	Logger logger = LogManager.getLogger(Poc.class);
	
	public void log()
	{
		logger.info("This is poc for logging if it is works or not");
		logger.debug(1);
		logger.info(2);
		logger.warn(3);
		logger.error(4);
		logger.fatal(5);
	}
	
	UserRepo userRepo;
	
	@Autowired
	public void connection(UserRepo y)
	{
		//log.debug("****************************************");
		//log.debug("autowired successfully");
		userRepo =y;
	}
}