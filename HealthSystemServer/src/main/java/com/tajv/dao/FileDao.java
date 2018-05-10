package com.tajv.dao;

import java.util.List;

import com.tajv.model.File;

public interface FileDao {

	public void addFile(File uploadFile);

	public void deleteFile(int fileId);

	public File getFileById(int fileId);

	public List<File> getFileListByClientId(int clientId);
}