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
import org.springframework.stereotype.Controller;
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

	@RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = { "multipart/form-data" })
	public @ResponseBody void storeAd(@RequestPart("asd") String adString, @RequestPart("file") MultipartFile file)
			throws IOException {
		logger.info("entered controller");
		TestDto1 jsonAd = new ObjectMapper().readValue(adString, TestDto1.class);
		// do whatever you want with your file and jsonAd

	}

	@RequestMapping(value = "/up", method = RequestMethod.POST, consumes = { "multipart/form-data" })
	public @ResponseBody void storeAd1(@RequestParam("formdata") String s, MultipartHttpServletRequest request) {

		logger.info("message" + request.getFileNames());

		Iterator<String> it = request.getFileNames();
		logger.info(request.getFile("file").getSize());
		logger.info(request.getFile("file1").getSize());
		// do whatever you want with your file and jsonAd

	}

	@ResponseBody
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public String save(@RequestBody TestDto test) {
		String s = test.getImage();

		logger.info("username" + test.getUsername());
		logger.info("password" + test.getPassword());
		logger.info("image" + s);
		logger.info("hello" + test.getHello());
		byte[] decodedValue = Base64.getDecoder().decode(test.getImage());

		try {
			FileOutputStream f = new FileOutputStream(new File("/home/shalom/Pictures/tets1.png"));

			f.write(decodedValue);
			f.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.info("save");
		return "success";
	}

	@ResponseBody
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	public String saveget() {

		logger.info("get");
		return "success";
	}

	private static final Logger logger = Logger.getLogger(Form.class);
}
