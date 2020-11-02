package edu.osu.cse5234.business;

import edu.osu.cse5234.business.view.Inventory;
import edu.osu.cse5234.business.view.InventoryService;
import edu.osu.cse5234.model.Item;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class InventoryServiceBean
 */
@Stateless
@Remote(InventoryService.class)
public class InventoryServiceBean implements InventoryService {
	@PersistenceContext
	EntityManager entityManager;
	
	public final String MY_QUERY = "Select i from Item i";
	
    /**
     * Default constructor. 
     */
    public InventoryServiceBean() {
        // TODO Auto-generated constructor stub
    }
    
    public Inventory getAvailableInventory() {
    	
    	Inventory inventory = new Inventory();
    	List<Item> storeItems = entityManager.createQuery(MY_QUERY, Item.class).getResultList();
		inventory.setListofItems(storeItems);
		
		return inventory;
    }
    
	public boolean validateQuantity(List<Item> items) {
//		Inventory inventory = getAvailableInventory();
//		List<Item> storeItems = inventory.getListofItems();
//		for(int x = 0; x < storeItems.size(); x++) {
//			int userQuantity = Integer.parseInt(items.get(x).getQuantity());
//			int storeQuantity = Integer.parseInt(storeItems.get(x).getQuantity());
//			
//			if(userQuantity > storeQuantity) {
//				return false;
//			}
//			
//		}
		return true;
	}
	public boolean updateInventory(List<Item> items) {
		return true;
	}

}
