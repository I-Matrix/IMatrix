package com.imatrix.web.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;



@RestController
public class HomeCtrl {
	@GetMapping(path="/")  	
	public ModelAndView homePage() {
		return new ModelAndView("home");
	}
}
