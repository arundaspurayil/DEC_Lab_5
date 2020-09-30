package edu.osu.cse5234.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {
	
	List<Item> storeItems = new ArrayList<>();

	
	@RequestMapping(method= RequestMethod.GET)
	public String viewOrderEntryForm(HttpServletRequest request, HttpServletResponse response) throws Exception{
		if(storeItems.size() == 0) {
			Item golfBalls = new Item();
			golfBalls.setName("Golf Balls");
			golfBalls.setPrice("1");
			golfBalls.setQuantity("100");
			
			Item wedge = new Item();
			wedge.setName("Wedge");
			wedge.setPrice("100");
			wedge.setQuantity("25");
			
			Item driver = new Item();
			driver.setName("Driver");
			driver.setPrice("400");
			driver.setQuantity("5");
			
			Item golfShoes = new Item();
			golfShoes.setName("Golf Shoes");
			golfShoes.setPrice("25");
			golfShoes.setQuantity("200");
			
			Item golfGloves = new Item();
			golfGloves.setName("Golf Gloves");
			golfGloves.setPrice("5");
			golfGloves.setQuantity("50");
			
			storeItems.add(golfBalls);
			storeItems.add(wedge);
			storeItems.add(driver);
			storeItems.add(golfShoes);
			storeItems.add(golfGloves);
		}
		
		Order order = new Order();
		order.setItems(storeItems);
		
		request.setAttribute("order", order);
		return "OrderEntryForm";
	}
	
	@RequestMapping(path = "/submitItems", method = RequestMethod.POST)
	public String submitItems(@ModelAttribute("order") Order order, HttpServletRequest request) {
		List<Item> userItems = order.getItems();
		
		for(int x = 0; x < storeItems.size(); x++) {
			int userQuantity = Integer.parseInt(userItems.get(x).getQuantity());
			int storeQuantity = Integer.parseInt(this.storeItems.get(x).getQuantity());
			
			if(!(userQuantity < storeQuantity)) {
				//TODO: Throw validation error
			}
		}
		
		request.getSession().setAttribute("order", order);
		return "redirect:/purchase/paymentEntry";
	}
	
	@RequestMapping(path = "/paymentEntry", method = RequestMethod.GET)
	public String viewPaymentEntryPage(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("payment", new PaymentInfo());	
		return "PaymentEntryForm";
	}
	
	@RequestMapping(path = "/submitPayment", method = RequestMethod.POST)
	public String submitPayment(HttpServletRequest request, HttpServletResponse response) {
		//TODO: Validate payment info
		
		List<Item> userItems = (List<Item>) request.getSession().getAttribute("order");
		
		for(int x = 0; x < storeItems.size(); x++) {
			int userQuantity = Integer.parseInt(userItems.get(x).getQuantity());
			int storeQuantity = Integer.parseInt(this.storeItems.get(x).getQuantity());
			
			int newQuantity = storeQuantity - userQuantity;
			this.storeItems.get(x).setQuantity(Integer.toString(newQuantity));
		}
		
		request.setAttribute("payment", new PaymentInfo());	
		return "PaymentEntryForm";
	}
	
	@RequestMapping(path = "/shippingEntry", method = RequestMethod.GET)
	public String shippingEntry(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("payment", new PaymentInfo());	
		return "ShippingEntryForm";
	}
	
	@RequestMapping(path = "/submitShipping", method = RequestMethod.POST)
	public String submitShipping(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("payment", new PaymentInfo());	
		return "ShippingEntryForm";
	}
	
	@RequestMapping(path = "/viewOrder", method = RequestMethod.GET)
	public String viewOrder(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("payment", new PaymentInfo());	
		return "ShippingEntryForm";
	}
	
	@RequestMapping(path = "/confirmOrder", method = RequestMethod.POST)
	public String confirmOrder(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("payment", new PaymentInfo());	
		return "ShippingEntryForm";
	}
	
	@RequestMapping(path = "/viewConfirmation", method = RequestMethod.GET)
	public String viewConfirmation(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("payment", new PaymentInfo());	
		return "ShippingEntryForm";
	}



}
