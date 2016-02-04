package com.brian.app.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brian.app.model.Department;
import com.brian.app.services.TestService;

@Controller
@RequestMapping(value="/test")
public class TestController {
@Autowired
TestService testService;
@RequestMapping(value="/save")
public void save()
{
	testService.save();
}

@RequestMapping(value="/get", method=RequestMethod.GET)
@ResponseBody
public List<Department>  get()
{
	 List<Department> department=testService.list();
	 for(int i=0;i<department.size();i++)
	 {
		 for(int s=0;s<department.get(i).getEmployees().size();s++)
		logger.info("praise god"+department.get(i).getEmployees().get(s));
	 }
	 return department;
	 }
private static final Logger logger = Logger.getLogger(TestController.class);
}
