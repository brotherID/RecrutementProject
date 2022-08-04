package dev.procheck.recuirement.pk.recuirement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidacyDto {
	private  String town; 
	private String borough;
	private String skills_area; 
	private String level; 
	private String experience;
}
