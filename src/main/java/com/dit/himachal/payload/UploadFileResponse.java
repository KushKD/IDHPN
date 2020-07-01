package com.dit.himachal.payload;

import com.dit.himachal.entities.VehicleOwnerEntries;

/**
 * @author kush
 *
 */
public class UploadFileResponse {
	
	 private String fileName;
	    private String fileDownloadUri;
	    private String fileType;
	    private long size;
	    private VehicleOwnerEntries ownerData;
	    private String generateIDCardUrl_;

	    public UploadFileResponse(String fileName, String fileDownloadUri, String generateIDCardUrl, String fileType, long size, VehicleOwnerEntries owner) {
	        this.fileName = fileName;
	        this.fileDownloadUri = fileDownloadUri;
	        this.fileType = fileType;
	        this.size = size;
	        this.ownerData = owner;
	        this.generateIDCardUrl_ = generateIDCardUrl;
	    }

	    public String getFileName() {
	        return fileName;
	    }

	    public void setFileName(String fileName) {
	        this.fileName = fileName;
	    }

	    public String getFileDownloadUri() {
	        return fileDownloadUri;
	    }

	    public void setFileDownloadUri(String fileDownloadUri) {
	        this.fileDownloadUri = fileDownloadUri;
	    }

	    public String getFileType() {
	        return fileType;
	    }

	    public void setFileType(String fileType) {
	        this.fileType = fileType;
	    }

	    public long getSize() {
	        return size;
	    }

	    public void setSize(long size) {
	        this.size = size;
	    }

		public VehicleOwnerEntries getOwnerData() {
			return ownerData;
		}

		public void setOwnerData(VehicleOwnerEntries ownerData) {
			this.ownerData = ownerData;
		}

	public String getGenerateIDCardUrl_() {
		return generateIDCardUrl_;
	}

	public void setGenerateIDCardUrl_(String generateIDCardUrl_) {
		this.generateIDCardUrl_ = generateIDCardUrl_;
	}
}
