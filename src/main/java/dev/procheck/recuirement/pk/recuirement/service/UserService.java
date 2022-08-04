package dev.procheck.recuirement.pk.recuirement.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import dev.procheck.recuirement.pk.recuirement.entity.AppUser;

public interface UserService {
	AppUser addNewUser(AppUser user);
	UserDetails loadUserByUsername(String username);
	List<AppUser> listUsers();
}