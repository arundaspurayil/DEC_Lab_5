package edu.osu.cse5234.business;

import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.osu.cse5234.business.view.Inventory;
import edu.osu.cse5234.business.view.InventoryService;
import edu.osu.cse5234.model.Order;
import edu.osu.cse5234.util.ServiceLocator;

/**
 * Session Bean implementation class OrderProcessingServiceBean
 */
@Resource(name="jms/emailQCF", lookup="jms/emailQCF", type=ConnectionFactory.class) 
@Stateless
@LocalBean
public class OrderProcessingServiceBean {

	@PersistenceContext
	EntityManager entityManager;
	
	@Inject
	@JMSConnectionFactory("java:comp/env/jms/emailQCF")
	private JMSContext jmsContext;

	@Resource(lookup="jms/emailQ")
	private Queue queue;

	
    /**
     * Default constructor. 
     */
    public OrderProcessingServiceBean() {
        // TODO Auto-generated constructor stub
    }
    
    private void notifyUser(String customerEmail) {
    	String message = customerEmail + ":" +
    		       "Your order was successfully submitted. " + 
    		     	"You will hear from us when items are shipped. " + 
    		      	new Date();
    	System.out.println("Sending message: " + message);
    	jmsContext.createProducer().send(queue, message);
    	System.out.println("Message Sent!");

    	

    }
    
    public String processOrder(Order order) {
    	boolean isOrderValid = validateItemAvailability(order);
    	if (isOrderValid) {
    		entityManager.persist(order);
    		entityManager.flush();
    		notifyUser(order.getEmailAddress());
    		return "2BN15627";
    	}
    	return "error";
    }
    
    public boolean validateItemAvailability(Order order) {
    	InventoryService inventoryService = ServiceLocator.getInventoryService();
    	Inventory inventory = inventoryService.getAvailableInventory();
    	for(int i=0; i<inventory.getListofItems().size(); i++) {
    		for(int j=0; j<order.getLineItems().size(); j++) {
    			if (order.getLineItems().get(j).getItemNumber() ==
    					(inventory.getListofItems().get(i).getItemNumber())) {
    				if (order.getLineItems().get(j).getQuantity() >
    						inventory.getListofItems().get(i).getQuantity()) {
    					return false;
    				}
    			}
    		}
    	}
//    	return inventoryService.validateQuantity(order.getItems());
    	return true;
    }

}
