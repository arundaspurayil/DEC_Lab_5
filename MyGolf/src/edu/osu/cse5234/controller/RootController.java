package edu.osu.cse5234.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;




@Controller
@RequestMapping("/")
public class RootController {
	

	
	@RequestMapping(method = RequestMethod.GET)
	public String viewHomePage(HttpServletRequest request, HttpServletResponse response) {
		return "Home";
	}
	
	@RequestMapping(path = "/quantity", method = RequestMethod.GET)
	@ResponseBody
	public String checkQuantities(HttpServletRequest request, HttpServletResponse response) {
		String error = "";
		
		try {
			int golfBallsQuantity = Integer.parseInt(request.getParameter("golfBallsQuantity"));
			int wedgeQuantity = Integer.parseInt(request.getParameter("wedgeQuantity"));
			int driverQuantity = Integer.parseInt(request.getParameter("driverQuantity"));
			int golfShoesQuantity = Integer.parseInt(request.getParameter("golfShoesQuantity"));
			int golfGlovesQuantity = Integer.parseInt(request.getParameter("golfGlovesQuantity"));
			
			if(golfBallsQuantity>200) {
				error += "Only 200 Golf Balls in Stock.";
			}
			if(wedgeQuantity > 50) {
				error += "Only 50 Wedges in Stock.";
			}
			if(driverQuantity > 25) {
				error += "Only 25 Drivers in Stock.";
			}
			if(golfShoesQuantity > 100) {
				error += "Only 100 Golf Shoes in Stock.";
			}
			if(golfGlovesQuantity > 5) {
				error += "Only 5 Golf Gloves in Stock.";
			}
		}catch(Exception e) {
			error += "Invalid Quantity.\n";
		}
		System.out.println(error);
		return error;
		
	}
	
	@RequestMapping(path = "/aboutUs", method = RequestMethod.GET)
	public String displayAboutUs(HttpServletRequest request, HttpServletResponse response) {
		
		return "AboutUs";
	}
	
	@RequestMapping(path = "/contactUs", method = RequestMethod.GET)
	public String displayContactUs(HttpServletRequest request, HttpServletResponse response) {
		
		return "ContactUs";
	}


}
