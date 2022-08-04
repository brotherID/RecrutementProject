package dev.procheck.recuirement.pk.recuirement.api;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.procheck.recuirement.pk.recuirement.service.ResumeDownloadService;

@RestController
@RequestMapping("/recuirement")
public class ResumeDownloadAPI {

	/// file:///D:/cvtheque/name19_firstName19/gestion%20des%20demandes2.png

	@Autowired
	private ResumeDownloadService resumeDownloadService;
	
	@CrossOrigin(allowedHeaders = "*", origins = "*", methods = { RequestMethod.DELETE, RequestMethod.GET,
			RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PATCH, RequestMethod.POST, RequestMethod.PUT,
			RequestMethod.TRACE })
	@GetMapping("download/resume")
	public void downloadResume(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("idCandidacy") String idCandidacy,
			@RequestParam(value="contentDispositionType",defaultValue = "inline") String contentDispositionType)throws IOException {

		resumeDownloadService.downloadResume(response, idCandidacy,contentDispositionType);

	}

}
