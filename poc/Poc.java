package project.farmease.poc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import project.farmease.models.UserRepo;

@RestController
public class Poc {

	//logger test poc and connection tested here
	Logger log = LogManager.getLogger(Poc.class);
	
	public void log()
	{
		log.info("This is poc for logging if it is works or not");
		log.debug(1);
		log.info(2);
		log.warn(3);
		log.error(4);
		log.fatal(5);
	}
	
	UserRepo r;
	
	@Autowired
	public void connection(UserRepo y)
	{
		//log.debug("****************************************");
		//log.debug("autowired successfully");
		r =y;
	}
}
