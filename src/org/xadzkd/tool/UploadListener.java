package org.xadzkd.tool;

import org.apache.commons.fileupload.ProgressListener;

public class UploadListener implements ProgressListener{
	private UploadStatus status;
	
	public UploadListener(){
		status = new UploadStatus();
	}
	
	@Override
	public void update(long bytesRead, long contentLength, int items){
		status.setBytesRead(bytesRead);
		status.setContentLength(contentLength);
		status.setItems(items);
		
	}
	
	
	
	public UploadStatus getStatus(){
		return this.status;
	}
}
