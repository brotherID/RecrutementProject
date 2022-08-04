package dev.procheck.recuirement.pk.recuirement.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {
	
	@Id
	private String idUser;
	private String userName;
	private String password;
	private String role;

}
