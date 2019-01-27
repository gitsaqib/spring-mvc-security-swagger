package com.saqibayub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.saqibayub.aop.RestControllerAspect;

@Controller
public class WelcomeController {

	@RequestMapping("/welcome")
	public ModelAndView helloWorld() {
 
		String message = "Hello! saqib";
		return new ModelAndView("welcome", "message", message);
	}
}
