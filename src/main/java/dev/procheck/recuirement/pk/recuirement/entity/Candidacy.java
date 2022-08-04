package dev.procheck.recuirement.pk.recuirement.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Candidacy {

	@Id
	private	String idCandidacy;
	private	String civility;
	private	String name;
	private	String firstName;
	private	int age;
	private	String telephone;
	private	String email;
	private	String town;
	private	String skillsArea;
	private	String borough;
	private	String level;
	private	String experience;
	private	String howDidYouKnowCompany;
	private	String pathResume;
	private String dateDemande;

}
