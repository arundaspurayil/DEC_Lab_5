package edu.osu.cse5234.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.osu.cse5234.model.Item;
import edu.osu.cse5234.model.Order;
import edu.osu.cse5234.model.PaymentInfo;
import edu.osu.cse5234.model.ShippingInfo;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {
	
	List<Item> storeItems = new ArrayList<>();
	String error = "";
	
	@RequestMapping(method= RequestMethod.GET)
	public String viewOrderEntryForm(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		handleErrors(request);
		if(storeItems.size() == 0) {
			Item golfBalls = new Item();
			golfBalls.setName("Golf Balls");
			golfBalls.setPrice("1");
			
			Item wedge = new Item();
			wedge.setName("Wedge");
			wedge.setPrice("100");
			
			Item driver = new Item();
			driver.setName("Driver");
			driver.setPrice("400");
			
			Item golfShoes = new Item();
			golfShoes.setName("Golf Shoes");
			golfShoes.setPrice("25");
			
			Item golfGloves = new Item();
			golfGloves.setName("Golf Gloves");
			golfGloves.setPrice("5");
			
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
		int total = 0;
		for(int x = 0; x < storeItems.size(); x++) {
			if (userItems.get(x).getQuantity().isEmpty()) {
				userItems.get(x).setQuantity("0");
			}
			if (!onlyDigits(userItems.get(x).getQuantity())) {
				error = userItems.get(x).getName() + "'s quantity is not allowed.\n";
				request.getSession().setAttribute("errors", error);
				return "redirect:/purchase";
			}
			int userQuantity = Integer.parseInt(userItems.get(x).getQuantity());
			
			if (userQuantity < 0) {
				error = userItems.get(x).getName() + "'s quantity cannot be negative.\n";
				request.getSession().setAttribute("errors", error);
				return "redirect:/purchase";
			}
			
			total += userQuantity * Integer.parseInt(userItems.get(x).getPrice());
		}
		
		request.getSession().setAttribute("total", total);
		request.getSession().setAttribute("order", order);
		return "redirect:/purchase/paymentEntry";
	}
	
	@RequestMapping(path = "/paymentEntry", method = RequestMethod.GET)
	public String viewPaymentEntryPage(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("payment", new PaymentInfo());	
		handleErrors(request);
		return "PaymentEntryForm";
	}
	
	@RequestMapping(path = "/submitPayment", method = RequestMethod.POST)
	public String submitPayment(@ModelAttribute("payment") PaymentInfo payment, HttpServletRequest request, HttpServletResponse response) {
		//TODO: Validate payment info
		
		if (payment.getCreditCardNumber().isEmpty() || payment.getCardHolderName().isEmpty() ||
				payment.getExpirationDate().isEmpty() || payment.getCvvCode().isEmpty()) {
			error = "Fields missing\n";
			request.getSession().setAttribute("errors", error);
			return "redirect:/purchase/paymentEntry";
		}
		
		if (payment.getCreditCardNumber().length() != 16 || !onlyDigits(payment.getCreditCardNumber())) {
			error = "Wrong Credit card number\n";
			request.getSession().setAttribute("errors", error);
			return "redirect:/purchase/paymentEntry";
		}
		
		if (!validateExpiryDate(payment.getExpirationDate())) {
			error = "Wrong Expiry Date\n";
			request.getSession().setAttribute("errors", error);
			return "redirect:/purchase/paymentEntry";
		}
		
		if (payment.getCvvCode().length() != 3 || !onlyDigits(payment.getCvvCode())) {
			error = "Wrong cvv code\n";
			request.getSession().setAttribute("errors", error);
			return "redirect:/purchase/paymentEntry";
		}
		
		request.getSession().setAttribute("payment", payment);
		return "redirect:/purchase/shippingEntry";
		
	}
	
	@RequestMapping(path = "/shippingEntry", method = RequestMethod.GET)
	public String shippingEntry(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("shipping", new ShippingInfo());	
		handleErrors(request);
		return "ShippingEntryForm";
	}
	
	@RequestMapping(path = "/submitShipping", method = RequestMethod.POST)
	public String submitShipping(@ModelAttribute("shipping") ShippingInfo shipping, HttpServletRequest request, HttpServletResponse response) {
		
		if (shipping.getName().isEmpty() || shipping.getAddressLine1().isEmpty() ||
				shipping.getCity().isEmpty() || shipping.getAddressLine2().isEmpty() ||
				shipping.getState().isEmpty() || shipping.getZip().isEmpty()) {
			error = "Fields missing\n";
			request.getSession().setAttribute("errors", error);
			return "redirect:/purchase/shippingEntry";
		}
		if (shipping.getZip().length() != 5 || !onlyDigits(shipping.getZip())) {
			error = "Wrong zipcode\n";
			request.getSession().setAttribute("errors", error);
			return "redirect:/purchase/shippingEntry";
		}
		
		request.getSession().setAttribute("shipping", shipping);
		
		return "redirect:/purchase/viewOrder";
	}
	
	@RequestMapping(path = "/viewOrder", method = RequestMethod.GET)
	public String viewOrder(HttpServletRequest request, HttpServletResponse response) {
		
		return "ViewOrder";
	}
	
	@RequestMapping(path = "/confirmOrder", method = RequestMethod.POST)
	public String confirmOrder(HttpServletRequest request, HttpServletResponse response) {
		
		return "redirect:/purchase/viewConfirmation";
	}
	
	@RequestMapping(path = "/viewConfirmation", method = RequestMethod.GET)
	public String viewConfirmation(HttpServletRequest request, HttpServletResponse response) {
		
		return "Confirmation";
	}
	
	private boolean onlyDigits(String str) {
		int n = str.length();
		for (int i=0; i<n; i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	private boolean validateExpiryDate(String date) {
		return date.compareTo(new SimpleDateFormat("MM/yy").format(new Date())) < 1? false : true;
	}
	
	private void handleErrors(HttpServletRequest request) {
		if (request.getSession().getAttribute("errors") != null) {
			request.setAttribute("errors", request.getSession().getAttribute("errors"));
			request.getSession().removeAttribute("errors");
		}
	}



}
