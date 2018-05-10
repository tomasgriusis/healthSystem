package com.tajv.service;

import java.util.List;

import com.tajv.model.File;

public interface FileService {

	public void addFile(File file);

	public void deleteFile(int fileId);

	public File getFileById(int fileId);

	public List<File> getFileListByClientId(int clientId);

	public String getFileDataListByFile(File file);
}
