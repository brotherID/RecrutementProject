package dev.procheck.recuirement.pk.recuirement.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.procheck.recuirement.pk.recuirement.entity.AppUser;
import dev.procheck.recuirement.pk.recuirement.jpa.UserRepository;
import lombok.AllArgsConstructor;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	

	@Override
	public AppUser addNewUser(AppUser user) {
		String idUser = UUID.randomUUID().toString();
		user.setIdUser(idUser);
		//String password =user.getPassword();
		//user.setPassword(passwordEncoder.encode(password));
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		Optional<AppUser> appUserOptional= userRepository.findByUserName(username);

		if (appUserOptional.isPresent()) {
			AppUser appUser = appUserOptional.get();
			Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority(appUser.getRole()));

			return new User(appUser.getUserName(), appUser.getPassword(), authorities);

		}

		return null;
	}

	@Override
	public List<AppUser> listUsers() {
		return userRepository.findAll();
	}

}
