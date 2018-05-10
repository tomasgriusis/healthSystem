package com.tajv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FILES")
public class File {

	@Id
	@GeneratedValue
	@Column(name = "FILE_ID")
	private int fileId;

	@Column(name = "FILE_NAME")
	private String fileName;

	@Column(name = "FILE_DATA")
	private byte[] data;

	@Column(name = "CLIENT_ID")
	private int clientId;

	/* private ArrayList<Integer> heartRateData; */

	public long getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public long getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	/*
	 * public ArrayList<Integer> getHeartRateData() { return heartRateData; }
	 * 
	 * public void setHeartRateData(ArrayList<Integer> heartRateData) {
	 * this.heartRateData = heartRateData; }
	 */
}