package edu.osu.cse5234.business;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.osu.cse5234.business.view.Inventory;
import edu.osu.cse5234.business.view.InventoryService;
import edu.osu.cse5234.model.Order;
import edu.osu.cse5234.util.ServiceLocator;

/**
 * Session Bean implementation class OrderProcessingServiceBean
 */
@Stateless
@LocalBean
public class OrderProcessingServiceBean {

	@PersistenceContext
	EntityManager entityManager;
	
    /**
     * Default constructor. 
     */
    public OrderProcessingServiceBean() {
        // TODO Auto-generated constructor stub
    }
    
    public String processOrder(Order order) {
    	boolean isOrderValid = validateItemAvailability(order);
    	if (isOrderValid) {
    		entityManager.persist(order);
    		entityManager.flush();
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
