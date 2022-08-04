package dev.procheck.recuirement.pk.recuirement.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.procheck.recuirement.pk.recuirement.entity.AppUser;


public interface UserRepository extends JpaRepository<AppUser, String>{
	Optional<AppUser> findByUserName(String username);
	
	@Query("SELECT appuser from AppUser appuser WHERE appuser.userName = :username and  appuser.password = :password")
	AppUser findByUserNameAndPassword(@Param("username")String username,@Param("password")String password);
	
}
