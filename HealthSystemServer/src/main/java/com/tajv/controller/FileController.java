package com.tajv.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tajv.model.Client;
import com.tajv.model.File;
import com.tajv.service.FileService;

@RestController
public class FileController {

	private FileService fileService;

	@Autowired
	@Qualifier(value = "fileService")
	public void setClientService(FileService fileService) {
		this.fileService = fileService;
	}

	@RequestMapping(value = "/addFile", method = RequestMethod.POST)
	public ResponseEntity<String> handleFileUpload(HttpServletRequest request, @RequestBody File file)
			throws Exception {

		System.out.println("\nCONNECTED TO SERVER...");

		fileService.addFile(file);

		System.out.println("File saved: " + file.getFileName());

		return new ResponseEntity<String>("File successfully added!", HttpStatus.OK);

	}

	@RequestMapping(value = "/getFileList", method = RequestMethod.POST)
	public @ResponseBody List<File> getFileList(HttpServletResponse response, @RequestBody Client client,
			HttpSession session) throws Exception {

		System.out.println("\nCONNECTED TO SERVER...");

		List<File> fileList = fileService.getFileListByClientId(client.getId());

		return fileList;
	}

	@RequestMapping(value = "/getFile/{fileId}", method = RequestMethod.GET)
	public ResponseEntity<String> getFile(HttpServletResponse response, @PathVariable int fileId) throws Exception {

		/*
		 * System.out.println("CONNECTED TO SERVER...");
		 * 
		 * File fileToGet = fileService.getFileById(fileId); if (fileToGet == null) {
		 * return new ResponseEntity<String>("File doesn't exist!",
		 * HttpStatus.NOT_FOUND); } if ( fileToGet.getClientId() == clientId &&
		 * fileToGet.getFileId() == fileId) { String file =
		 * mapper.writeValueAsString(fileToGet); return new ResponseEntity<String>(file,
		 * HttpStatus.OK); }
		 */
		return new ResponseEntity<String>("Client id and file id do not match!", HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/getFileDataList", method = RequestMethod.POST)
	public @ResponseBody String getFileDataList(HttpServletResponse response, @RequestBody File file) throws Exception {

		System.out.println("\nCONNECTED TO SERVER...");

		String heartRateData = fileService.getFileDataListByFile(file);

		return heartRateData;
	}
}
