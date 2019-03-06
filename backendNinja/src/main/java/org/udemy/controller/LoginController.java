package org.udemy.controller;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.udemy.constant.ViewConstant;

@Controller
public class LoginController {
	
	private static final Log LOG = LogFactory.getLog(LoginController.class);
		
	@GetMapping("/login")
	public String showLoginForm(Model model,
			@RequestParam(name="error", required=false)String error,
			@RequestParam(name="logout", required=false)String logout) {
		LOG.info("METHOD: showLoginForm() -- PARAMS: error="+ error+" logout="+ logout);
		model.addAttribute("error", error );
		model.addAttribute("logout", logout );
		LOG.info("Returning to Login View");
		return ViewConstant.LOGIN_VIEW;
	}
	
	@GetMapping({"/loginsuccess", "/"})
	public String loginCheck() {
		LOG.info("METHOD: loginCheck()");
		LOG.info("Returning to Contacts View");
		return "redirect:/contacts/showcontacts";

	}

}
