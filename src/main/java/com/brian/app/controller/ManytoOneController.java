package com.brian.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.brian.app.services.TestMAnytoOneService;


@Controller
@RequestMapping(value="/testmanytoone")
public class ManytoOneController {
	@Autowired
	TestMAnytoOneService testManytoOneService;
	
	@RequestMapping(value="/manytoone" ,method=RequestMethod.GET)
	public void save()
	{
		testManytoOneService.save();;
	}
}
