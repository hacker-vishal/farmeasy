package project.farmease.controller;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.farmease.dto.Response;
import project.farmease.pojo.Hostuser;
import project.farmease.service.HomeAndSearchService;

 
@RestController
@RequestMapping("/services")  
public class HomeAndSearchController {
	Logger logger = LogManager.getLogger(HomeAndSearchController.class);
	
	@Autowired
	private HomeAndSearchService homeAndSearchService;

	@CrossOrigin//("http://localhost:4200")
	@PostMapping("/searchserv")
	public List<Hostuser> searchforservice(@RequestBody Hostuser hostuser) 
	{
		return homeAndSearchService.searchforservice(hostuser);
	}
	
	@CrossOrigin//("http://localhost:4200")
	@PostMapping("/insert")
	public Response registerhost(@RequestBody Hostuser hostuser) {
		
		return homeAndSearchService.registerhost(hostuser);
	}

	//poc on test case
	public int sum(int i, int j) 
	{
		return i+j;
	}	
}
