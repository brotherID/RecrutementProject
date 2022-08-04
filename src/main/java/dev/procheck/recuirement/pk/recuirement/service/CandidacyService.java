package dev.procheck.recuirement.pk.recuirement.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import dev.procheck.recuirement.pk.recuirement.dto.CandidacyDto;
import dev.procheck.recuirement.pk.recuirement.entity.Candidacy;

public interface CandidacyService {
	
	public  Page<Candidacy>   getAllCandidaciesWithPagination(String size,String page);
    public  List<Candidacy>   getAllCandidacies();
    public  Page<Candidacy>   getCandidacy(String town, 
								 String borough,
								 String skills_area, 
								 String level, 
								 String experience,
								 String size,
								 String page);
    public  Candidacy saveCandidacy(MultipartFile resumeFile, String candidacyJsonAsString) throws  Exception;
    public  Page<Candidacy>   getCandidacyPagination(CandidacyDto candidacyDto,String size,String page);
}
