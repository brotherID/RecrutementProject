package dev.procheck.recuirement.pk.recuirement.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import dev.procheck.recuirement.pk.recuirement.entity.Candidacy;
import dev.procheck.recuirement.pk.recuirement.jpa.CandidacyRepository;

@Service
public class ResumeDownloadServiceImpl implements ResumeDownloadService {

	@Autowired
	private CandidacyRepository candidacyRepository;

	@Override
	public void downloadResume(HttpServletResponse response, String idCandidacy, String contentDispositionType)
			throws IOException {
		Candidacy candidacy = candidacyRepository.getOne(idCandidacy);
		File file = new File(candidacy.getPathResume());
		if (file.exists()) {

			// get the mimetype
			String mimeType = URLConnection.guessContentTypeFromName(file.getName());
			if (mimeType == null) {
				// unknown mimetype so set the mimetype to application/octet-stream
				mimeType = "application/octet-stream";
			}
			response.setContentType(mimeType);
			response.setHeader("Content-Disposition", getContentDisposition(contentDispositionType, file));
			response.setContentLength((int) file.length());
			InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
			FileCopyUtils.copy(inputStream, response.getOutputStream());
		}

	}

	String getContentDisposition(String contentDispositionType, File file) {
		if (!Arrays.asList("attachment", "inline").contains(contentDispositionType)) {
			contentDispositionType = "inline";
		}
		String contentDisposition = String.format(contentDispositionType + "; filename=\"" + file.getName() + "\"");
		return contentDisposition;
	}

}
