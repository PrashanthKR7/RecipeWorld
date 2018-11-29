package com.shiksha.recipesAPI.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.shiksha.recipesAPI.payload.Response;
import com.shiksha.recipesAPI.payload.UploadResponse;
import com.shiksha.recipesAPI.service.DefaultUploadService;

@RestController
@RequestMapping(value = "/storage")
public class StorageController {
	final static Logger LOGGER = LogManager.getLogger();

	@Autowired
	private DefaultUploadService fileUploadService;
	@Autowired
	private MessageSource messageSource;
	
	private UploadResponse uploadFileAndRespond(MultipartFile file) {
		String fileName = fileUploadService.storeFile(file);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/storage/download/").path(fileName)
				.toUriString();
		return new UploadResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
	}

	@RequestMapping(method = RequestMethod.POST, value = "/upload")
	public ResponseEntity<Response<UploadResponse>> uploadFile(@RequestParam("file") MultipartFile file,
			HttpServletRequest request) {
	
		UploadResponse image = uploadFileAndRespond(file);
		Response<UploadResponse> response = new Response<>(HttpStatus.CREATED.value(), null,
				messageSource.getMessage("file.uploaded", new String[] { image.getFileName() }, Locale.ROOT),
				request.getRequestURI(), image, HttpStatus.CREATED.getReasonPhrase());

		return new ResponseEntity<Response<UploadResponse>>(response, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/uploadMultiple")
	public ResponseEntity<Response<List<UploadResponse>>> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files, HttpServletRequest request) {
		List<UploadResponse> imageList =  Arrays.asList(files).stream().map(file -> uploadFileAndRespond(file)).collect(Collectors.toList());
		
		Response<List<UploadResponse>> response = new Response<>(HttpStatus.CREATED.value(), null,
				messageSource.getMessage("file.multiple_uploaded", new String[] {}, Locale.ROOT),
				request.getRequestURI(), imageList, HttpStatus.CREATED.getReasonPhrase());

		return new ResponseEntity<Response<List<UploadResponse>>>(response, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/download/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
		// Load file as Resource
		Resource resource = fileUploadService.loadFileAsResource(fileName);

		// Try to determine file's content type
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			LOGGER.info("Could not determine file type.");
		}

		// Fallback to the default content type if type could not be determined
		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

}
