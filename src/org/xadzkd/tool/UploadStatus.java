package org.xadzkd.tool;

public class UploadStatus {
	private long bytesRead;
	private long contentLength;
	private long items;
	private long startTime = System.currentTimeMillis();
	public long getBytesRead() {
		return bytesRead;
	}
	public void setBytesRead(long bytesRead) {
		this.bytesRead = bytesRead;
	}
	public long getStartTime() {
		return startTime;
	}
	
	public long getContentLength() {
		return contentLength;
	}
	public void setContentLength(long contentLength) {
		this.contentLength = contentLength;
	}
	public long getItems() {
		return items;
	}
	public void setItems(long items) {
		this.items = items;
	}
	
	public void start(){
		startTime = System.currentTimeMillis();
	}
}
