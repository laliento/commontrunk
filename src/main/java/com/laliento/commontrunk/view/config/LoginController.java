package com.laliento.commontrunk.view.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	@RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
	public String getHomePage() {
		return "redirect:/login.xhtml";
	}
	@RequestMapping(value = {"/user-user"}, method = RequestMethod.GET)
	public String getUserPage() {
		return "redirect:/pages/user/user.xhtml";
	}
	
}
