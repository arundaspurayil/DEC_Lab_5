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

import edu.osu.cse5234.business.OrderProcessingServiceBean;
import edu.osu.cse5234.business.view.Inventory;
import edu.osu.cse5234.business.view.InventoryService;
import edu.osu.cse5234.model.LineItem;
import edu.osu.cse5234.model.Order;
import edu.osu.cse5234.model.PaymentInfo;
import edu.osu.cse5234.model.ShippingInfo;
import edu.osu.cse5234.util.ServiceLocator;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {

	String error = "";
	
	@RequestMapping(method= RequestMethod.GET)
	public String viewOrderEntryForm(HttpServletRequest request, HttpServletResponse response) throws Exception{
		 
		handleErrors(request);
		
		InventoryService inventoryService = ServiceLocator.getInventoryService();
		Inventory inventory = inventoryService.getAvailableInventory();
		
		List<LineItem> lineItems = new ArrayList<LineItem>();
		Order order = new Order();
		for (int i=0; i<inventory.getListofItems().size(); i++) {
			lineItems.add(new LineItem());
		}
		order.setLineItems(lineItems);
		
		request.setAttribute("inventory", inventory);
		request.setAttribute("order", order);
		return "OrderEntryForm";
	}
	
	@RequestMapping(path = "/submitItems", method = RequestMethod.POST)
	public String submitItems(@ModelAttribute("order") Order order, HttpServletRequest request) {
		List<LineItem> userItems = order.getLineItems();
		int total = 0;
		for(int x = 0; x < userItems.size(); x++) {

			if (!onlyDigits(Integer.toString(userItems.get(x).getQuantity()))) {
				error = userItems.get(x).getName() + "'s quantity is not allowed.\n";
				request.getSession().setAttribute("errors", error);
				return "redirect:/purchase";
			}
			int userQuantity = userItems.get(x).getQuantity();
			
			if (userQuantity < 0) {
				error = userItems.get(x).getName() + "'s quantity cannot be negative.\n";
				request.getSession().setAttribute("errors", error);
				return "redirect:/purchase";
			}
			
			total += userQuantity * userItems.get(x).getPrice();
		}
		
		OrderProcessingServiceBean orderProcessor = ServiceLocator.getOrderProcessingService();
		boolean validOrder = orderProcessor.validateItemAvailability(order); 
		if (!validOrder) {
			error = "Selected quantity more than in stock.\n";
			request.getSession().setAttribute("errors", error);
			return "redirect:/purchase";
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
		Order order = (Order)request.getSession().getAttribute("order");
		order.setPaymentInfo(payment);
		request.setAttribute("order", order);
		request.getSession().setAttribute("order", order);
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
		Order order = (Order)request.getSession().getAttribute("order");
		order.setShippingInfo(shipping);
		order.setCustomerName(shipping.getName());
		order.setEmailAddress(shipping.getEmailAddress());
		request.setAttribute("order", order);
		request.getSession().setAttribute("order", order);
		return "redirect:/purchase/viewOrder";
	}
	
	@RequestMapping(path = "/viewOrder", method = RequestMethod.GET)
	public String viewOrder(HttpServletRequest request, HttpServletResponse response) {
		
		return "ViewOrder";
	}
	
	@RequestMapping(path = "/confirmOrder", method = RequestMethod.POST)
	public String confirmOrder(HttpServletRequest request, HttpServletResponse response) {
		Order order = (Order) request.getSession().getAttribute("order");
		OrderProcessingServiceBean orderProcessor = ServiceLocator.getOrderProcessingService();
		String orderId = orderProcessor.processOrder(order);
		request.getSession().setAttribute("orderId", orderId);
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
