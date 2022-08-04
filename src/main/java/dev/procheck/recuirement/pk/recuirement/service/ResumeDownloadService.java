package dev.procheck.recuirement.pk.recuirement.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public interface ResumeDownloadService {
	
	void downloadResume( HttpServletResponse response,String idCandidacy, String contentDispositionType) throws IOException ;

}
