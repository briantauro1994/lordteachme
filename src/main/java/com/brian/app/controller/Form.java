package com.brian.app.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.brian.app.dto.TestDto;
import com.brian.app.dto.TestDto1;

@Controller
@RequestMapping(value = "/form")
public class Form {

	

	@RequestMapping(value = "/up", method = RequestMethod.POST, consumes = { "multipart/form-data" })
	public @ResponseBody ResponseEntity<String> storeAd1(  MultipartHttpServletRequest request) throws Exception {
try{
	
	String s=request.getParameter("formdata");
		logger.info("message" + s);

		Iterator<String> it = request.getFileNames();
//		logger.info(request.getFile("file").getSize());
//		logger.info(request.getFile("file1").getSize());
		// do whatever you want with your file and jsonAd
		return new ResponseEntity<>("successytesr", HttpStatus.ACCEPTED);
}
catch(Exception e)
{
	throw new Exception();
}
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> errorHandler(Exception exc) {
		logger.error(exc.getMessage(), exc);
		return new ResponseEntity<>("timepass", HttpStatus.BAD_REQUEST);
	}

	
	private static final Logger logger = Logger.getLogger(Form.class);
}
