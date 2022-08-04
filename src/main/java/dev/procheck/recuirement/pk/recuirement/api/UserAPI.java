package dev.procheck.recuirement.pk.recuirement.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.procheck.recuirement.pk.recuirement.entity.AppUser;
import dev.procheck.recuirement.pk.recuirement.service.UserService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/recuirement")
@Slf4j
public class UserAPI {
	
	@Autowired
	private UserService userService;
	
	
	@CrossOrigin(allowedHeaders = "*", origins = "*", methods = { RequestMethod.DELETE, RequestMethod.GET,
			RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PATCH, RequestMethod.POST, RequestMethod.PUT,
			RequestMethod.TRACE })
	@PostMapping("/user")
	public AppUser addUser(@RequestBody  AppUser user)
	{
		log.info("addUser *************************");
	    return  userService.addNewUser(user);
	}

	//@PreAuthorize("hasAuthority('user')")
	@CrossOrigin(allowedHeaders = "*", origins = "*", methods = { RequestMethod.DELETE, RequestMethod.GET,
			RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PATCH, RequestMethod.POST, RequestMethod.PUT,
			RequestMethod.TRACE })
	@GetMapping("/users")
	public List<AppUser> users()
	{
		log.info("users ...");
		List<AppUser> users = userService.listUsers();
		log.info("users DONE");
		return  users;
	}
	
	
}
