package com.tajv.service;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tajv.dao.FileDao;
import com.tajv.model.File;

@Service
@Transactional
public class FileServiceImpl implements FileService {

	@Autowired
	private FileDao fileDao;

	public void addFile(File file) {
		this.fileDao.addFile(file);
	}

	public void deleteFile(int fileId) {
		this.fileDao.deleteFile(fileId);
	}

	public File getFileById(int fileId) {
		return this.fileDao.getFileById(fileId);
	}

	public List<File> getFileListByClientId(int clientId) {
		return this.fileDao.getFileListByClientId(clientId);
	}

	public String getFileDataListByFile(File file) {

		StringBuffer data = new StringBuffer();

		String heartData = new String(file.getData(), StandardCharsets.UTF_8);

		heartData = heartData.split(Pattern.quote("[HRData]") + "\r\n")[1];
		heartData = heartData.replaceAll("\\s\n", ",");

		String[] heartRateData = heartData.split(",");

		for (int i = 0; i < heartRateData.length; i++) {
			if (heartRateData[i].length() > 0) {
				data.append(heartRateData[i]);
				if (i != heartRateData.length - 1) {
					data.append(",");
				}
			}
		}

		return data.toString();
	}
}
