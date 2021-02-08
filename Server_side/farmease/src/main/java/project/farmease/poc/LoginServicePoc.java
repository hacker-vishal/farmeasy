package project.farmease.poc;

import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.farmease.dao.UserRepo;
import project.farmease.dto.Response;
import project.farmease.dto.Userdto;
import project.farmease.pojo.User;

@Service
public class LoginServicePoc {
	
	Logger logger = LogManager.getLogger(LoginServicePoc.class);
	
	@Autowired
	private UserRepo userRepo;
	
	private Response response;
	private Optional<User> user;
	
	@Transactional
	public Response dologin(Userdto userdto) {

		response = new Response(0,"Check id and password!");
		
		//assume checked with db records
//		User user = new User("abc", "pabc", null);
		
		user = userRepo.findById(userdto.getUsername());
		
//		if (user.getEmail().equals(userdto.getUsername()) && user.getPassword().equals(userdto.getPassword())) {
		if (user.get().getEmail().equals(userdto.getUsername()) && user.get().getPassword().equals(userdto.getPassword())) {
			response.setStatus(1);
			response.setMessage("Logged in successfully!");
		}		
		return response;
	}

}
