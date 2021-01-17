package project.farmease;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import project.farmease.controller.HomeAndSearch;
import project.farmease.models.Hostuser;
import project.farmease.poc.Poc;

@SpringBootApplication
public class FarmeaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(FarmeaseApplication.class, args);
		
		Logger log = LogManager.getLogger(FarmeaseApplication.class);
		
//		Poc p = new Poc();
//		p.log();
		
	    log.debug(1);
		
		List<Hostuser> l = new ArrayList<Hostuser>();
		HomeAndSearch h = new HomeAndSearch();
		l=h.searchforservice("tractor", "pune");
		if(l==null)
			log.debug(11);
	}	
}