package org.xadzkd.tool;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class MyCommonsMultipartResolver extends CommonsMultipartResolver{
	
	@Override
	protected MultipartParsingResult parseRequest(HttpServletRequest request) throws MultipartException {
		String encoding = determineEncoding(request);
		 
		FileUpload fileUpload = prepareFileUpload(encoding);
		 
		fileUpload.setFileSizeMax(-1);
		 
		final HttpSession session = request.getSession();
		
		UploadListener listener = new UploadListener();
		fileUpload.setProgressListener(listener);
		session.setAttribute("status", listener.getStatus());
		try {
			 
			   List<FileItem> fileItems = ((ServletFileUpload) fileUpload).parseRequest(request);
			 
			   return parseFileItems(fileItems, encoding);
			 
			  } catch (FileUploadBase.SizeLimitExceededException ex) {
			 
			   throw new MaxUploadSizeExceededException(fileUpload.getSizeMax(),
			 
			     ex);
			 
			  } catch (FileUploadException ex) {
			 
			   throw new MultipartException(
			 
			     "Could not parse multipart servlet request", ex);
			 
			 }
	}
}
