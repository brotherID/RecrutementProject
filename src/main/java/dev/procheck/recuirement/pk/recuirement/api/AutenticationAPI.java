package dev.procheck.recuirement.pk.recuirement.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.procheck.recuirement.pk.recuirement.entity.AppUser;
import dev.procheck.recuirement.pk.recuirement.jpa.UserRepository;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/recuirement")
@Slf4j
public class AutenticationAPI {
	
	@Autowired
	private UserRepository userRepository;
	
	@CrossOrigin(allowedHeaders = "*", origins = "*", methods = { RequestMethod.DELETE, RequestMethod.GET,
			RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PATCH, RequestMethod.POST, RequestMethod.PUT,
			RequestMethod.TRACE })
	@PostMapping("/autenticate")
	public AppUser autenticate(@RequestBody AppUser userDto)
	{
		return  userRepository.findByUserNameAndPassword(userDto.getUserName(), userDto.getPassword());
	}

}
