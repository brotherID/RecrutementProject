package dev.procheck.recuirement.pk.recuirement.service;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.procheck.recuirement.pk.recuirement.dto.CandidacyDto;
import dev.procheck.recuirement.pk.recuirement.entity.Candidacy;
import dev.procheck.recuirement.pk.recuirement.jpa.CandidacyRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CandidacyServiceImpl implements CandidacyService {

	@Autowired
	private CandidacyRepository candidacyRepository;

	@Value("${file.upload-cv-dir}")
	private String FILE_CV_DIRECTORY;
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public Page<Candidacy> getAllCandidaciesWithPagination(String size, String page) {
		log.info("Begin getAllCandidaciesWithPagination input : [size : {}  page : {}]", size, page);
		Page<Candidacy> candidacies = candidacyRepository
				.findAll(PageRequest.of(Integer.valueOf(page), Integer.valueOf(size)));
		log.info("End getAllCandidaciesWithPagination input : [size : {}  page : {}] output : [candidacies : {}]", size,
				page, candidacies.getContent());
		return candidacies;
	}

	@Override
	public List<Candidacy> getAllCandidacies() {
		log.info("Begin getAllCandidacies() ");
		List<Candidacy> candidacies = candidacyRepository.findAll();
		log.info("End   getAllCandidacies() {}",candidacies);
		return candidacies;
	}

	@Override
	public Page<Candidacy> getCandidacy(String town, String borough, String skills_area, String level,
			String experience, String size, String page) {
		log.info("Begin getCandidacy input : [town : {}  borough : {} skills_area : {} level : {} experience : {} size : {} page : {} ] ",town,borough,skills_area,level,experience,size,page);
		Page<Candidacy> candidacies = candidacyRepository.findByTownAndByBoroughAndBySkills_areaAndByLevelAndByExperience(town, borough, skills_area,
				level, experience, PageRequest.of(Integer.valueOf(page), Integer.valueOf(size)));
		log.info("End getCandidacy input : [town : {}  borough : {} skills_area : {} level : {} experience : {} size : {} page : {} ] output : [candidacies : {}] ",town,borough,skills_area,level,experience,size,page,candidacies.getContent());
		return candidacies;
	}

	

	private void createNewFileResume(String pathdirectoryResumeName, MultipartFile resumeFile, String pathResumeFile)
			throws Exception {
		File directory = new File(pathdirectoryResumeName);
		if (!directory.exists()) {
			directory.mkdirs();
		}

		File resumeNewFile = new File(pathResumeFile);

		resumeNewFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(resumeNewFile);
		fos.write(resumeFile.getBytes());
		fos.close();
	}

	private Candidacy convertCandidacyJsonToObject(String candidacyJsonAsString)
			throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(candidacyJsonAsString, Candidacy.class);
	}

	@Override
	public Candidacy saveCandidacy(MultipartFile resumeFile, String candidacyJsonAsString) throws Exception {
		log.info("Begin saveCandidacy input : [resumeFile : {} candidacyJsonAsString : {}] ",resumeFile.getOriginalFilename(),candidacyJsonAsString);
		Candidacy candidacy = convertCandidacyJsonToObject(candidacyJsonAsString);
		String IdCandidacy = UUID.randomUUID().toString();
		String pathdirectoryResumeName = String.format(FILE_CV_DIRECTORY,
				candidacy.getName() + "_" + candidacy.getFirstName());
		String pathResumeFile = pathdirectoryResumeName + resumeFile.getOriginalFilename().replaceAll(" ", "_");
		createNewFileResume(pathdirectoryResumeName, resumeFile, pathResumeFile);
		candidacy.setIdCandidacy(IdCandidacy);
		candidacy.setPathResume(pathResumeFile);

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
		candidacy.setDateDemande(dateTimeFormatter.format(LocalDateTime.now()));
		
		Candidacy candidacySave =candidacyRepository.save(candidacy);
		log.info("End saveCandidacy input : [resumeFile : {} candidacyJsonAsString : {}] output : [candidacy : {} ] ",resumeFile.getOriginalFilename(),candidacyJsonAsString,candidacySave);
		return candidacySave;
	}

	@Override
	public Page<Candidacy> getCandidacyPagination(CandidacyDto candidacyDto,String size,String page) {
		log.info("Begin getCandidacyPagination input : [candidacyDto : {} size : {} page : {} ] ",candidacyDto,size,page);
		Page<Candidacy> candidacies = findCandidaciesByCandidacyDto(candidacyDto,PageRequest.of(Integer.valueOf(page), Integer.valueOf(size)));
		log.info("End getCandidacyPagination input   : [candidacyDto : {} size : {} page : {} ] output : [candidacies : {}] ",candidacyDto,size,page,candidacies.getContent());
		return candidacies;
	}
	
	
	
	
	private Page<Candidacy> findCandidaciesByCandidacyDto(CandidacyDto candidacyDto,PageRequest pageRequest) {
	        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	        CriteriaQuery<Candidacy> cq = cb.createQuery(Candidacy.class);
	        Root<Candidacy> candidacy = cq.from(Candidacy.class);
	        List<Predicate> predicates = new ArrayList<>();
	        if (candidacyDto.getTown() != null && candidacyDto.getTown() != "") {
	            predicates.add(cb.like(candidacy.get("town"), "%" + candidacyDto.getTown() + "%"));
	        }
	        if (candidacyDto.getBorough() != null && candidacyDto.getBorough() != "") {
	            predicates.add(cb.like(candidacy.get("borough"), "%" + candidacyDto.getBorough() + "%"));
	        }
	        if (candidacyDto.getSkills_area() != null && candidacyDto.getSkills_area()!= "") {
	            predicates.add(cb.like(candidacy.get("skillsArea"), "%" + candidacyDto.getSkills_area() + "%"));
	        }
	        if (candidacyDto.getLevel() != null && candidacyDto.getLevel()!="") {
	            predicates.add(cb.like(candidacy.get("level"), "%" + candidacyDto.getLevel() + "%"));
	        }
	        if (candidacyDto.getExperience() != null && candidacyDto.getExperience() !="" ) {
	            predicates.add(cb.like(candidacy.get("experience"), "%" + candidacyDto.getExperience() + "%"));
	        }
	        cq.where(predicates.toArray(new Predicate[predicates.size()]));
	        Query query = entityManager.createQuery(cq);
	        int pageNumber =pageRequest.getPageNumber();
	        int pageSize = pageRequest.getPageSize();
	        query.setFirstResult((pageNumber) * pageSize);
	        query.setMaxResults(pageSize);
	        List <Candidacy> candidacies = query.getResultList();
	        return new PageImpl<>(candidacies,pageRequest,candidacies.size());
    }
}
