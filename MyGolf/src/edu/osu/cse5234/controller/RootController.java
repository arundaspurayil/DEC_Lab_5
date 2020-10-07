package edu.osu.cse5234.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.osu.cse5234.model.PaymentInfo;




@Controller
@RequestMapping("/")
public class RootController {
	

	
	@RequestMapping(method = RequestMethod.GET)
	public String viewHomePage(HttpServletRequest request, HttpServletResponse response) {
		return "Home";
	}
	
	@RequestMapping(path = "/quantity", method = RequestMethod.POST)
	public String checkQuantities(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("payment", new PaymentInfo());	
		return "PaymentEntryForm";
	}
	
	


}
