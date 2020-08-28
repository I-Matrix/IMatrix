package com.imatrix.web.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * <h1>404</h1>
 * <p>
 * 		/error
 * </p>
 *
 * <p>
 * 	This is the controller
 * 	for the /error query
 * </p>
 *
 *
 * @author  Amanuel Bogale
 * @version 0.1
 * @since   2020-08-20
 */
@RestController
public class ErrorCtrl {

	@GetMapping("/error")
	public ModelAndView errorPage() {
		return new ModelAndView("error");
	}
}
