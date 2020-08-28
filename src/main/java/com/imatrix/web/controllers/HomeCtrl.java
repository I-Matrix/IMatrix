package com.imatrix.web.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


/**
 * <h1>Home page</h1>
 * <p>
 * 		/
 * </p>
 *
 * <p>
 * 	This is the controller
 * 	for the / query
 * </p>
 *
 *
 * @author  Amanuel Bogale
 * @version 0.1
 * @since   2020-08-20
 */
@RestController
public class HomeCtrl {
	@GetMapping(path="/")  	
	public ModelAndView homePage() {
		return new ModelAndView("home");
	}
}
