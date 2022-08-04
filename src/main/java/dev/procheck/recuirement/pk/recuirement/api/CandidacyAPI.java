package dev.procheck.recuirement.pk.recuirement.api;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import dev.procheck.recuirement.pk.recuirement.dto.CandidacyDto;
import dev.procheck.recuirement.pk.recuirement.entity.Candidacy;
import dev.procheck.recuirement.pk.recuirement.service.CandidacyService;
import dev.procheck.recuirement.pk.recuirement.service.ResumeDownloadService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CandidacyAPI {

	@Autowired
	private  CandidacyService candidacyService;
	@Autowired
	private ResumeDownloadService resumeDownloadService;

	@Value("${file.upload-cv-dir}")
	private String FILE_CV_DIRECTORY;

	@CrossOrigin(allowedHeaders = "*", origins = "*", methods = { RequestMethod.DELETE, RequestMethod.GET,
			RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PATCH, RequestMethod.POST, RequestMethod.PUT,
			RequestMethod.TRACE })
	@GetMapping("recuirement/candidacies")
	public List<Candidacy> getAllCandidacies() {
		return candidacyService.getAllCandidacies();
	}
	
	@CrossOrigin(allowedHeaders = "*", origins = "*", methods = { RequestMethod.DELETE, RequestMethod.GET,
			RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PATCH, RequestMethod.POST, RequestMethod.PUT,
			RequestMethod.TRACE })
	@GetMapping("recuirement/pagination/candidacies")
	public Page<Candidacy> getAllCandidaciesWithPagination(
			@RequestParam(defaultValue = Integer.MAX_VALUE + "") String size,
			@RequestParam(defaultValue = "0") String page) {
		log.info("page {}", page);
		log.info("page {}", size);
		return candidacyService.getAllCandidaciesWithPagination(size, page);
	}
	
	@CrossOrigin(allowedHeaders = "*", origins = "*", methods = { RequestMethod.DELETE, RequestMethod.GET,
			RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PATCH, RequestMethod.POST, RequestMethod.PUT,
			RequestMethod.TRACE })
	@GetMapping("recuirement/candidacy")
	public Page<Candidacy> getCandidacy(@RequestParam String town, 
			@RequestParam String borough,
			@RequestParam String skills_area, 
			@RequestParam String level, 
			@RequestParam String experience,
			@RequestParam(defaultValue = Integer.MAX_VALUE + "") String size,
			@RequestParam(defaultValue = "0") String page) {
		return candidacyService.getCandidacy(town, borough, skills_area, level, experience, size, page);
	}
	
	@CrossOrigin(allowedHeaders = "*", origins = "*", methods = { RequestMethod.DELETE, RequestMethod.GET,
			RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PATCH, RequestMethod.POST, RequestMethod.PUT,
			RequestMethod.TRACE })
	@PostMapping("recuirement/candidacy")
	public Candidacy saveCandidacy(/* @RequestBody Candidacy candidacy, */
			@RequestParam("resumeFile") MultipartFile resumeFile,
			@RequestParam("candidacyJsonAsString") String candidacyJsonAsString,
			 HttpServletResponse response) throws Exception {
		return  candidacyService.saveCandidacy(resumeFile, candidacyJsonAsString);
		//resumeDownloadService.downloadResume(response, candidacy.getIdCandidacy());
	}
	
	@CrossOrigin(allowedHeaders = "*", origins = "*", methods = { RequestMethod.DELETE, RequestMethod.GET,
			RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PATCH, RequestMethod.POST, RequestMethod.PUT,
			RequestMethod.TRACE })
	@PostMapping("recuirement/pagination/candidacy")
	public Page<Candidacy> getCandidacyPagination(@RequestBody CandidacyDto candidacyDto, 
			@RequestParam(defaultValue = Integer.MAX_VALUE + "") String limit,
			@RequestParam(defaultValue = "0") String page) {
		return candidacyService.getCandidacyPagination(candidacyDto, limit, page);
	}
	
	
	
	
}
