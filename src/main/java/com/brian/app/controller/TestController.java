package com.brian.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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


}
