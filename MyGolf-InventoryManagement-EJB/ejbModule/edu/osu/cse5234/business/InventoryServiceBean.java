package edu.osu.cse5234.business;

import edu.osu.cse5234.business.view.Inventory;
import edu.osu.cse5234.business.view.InventoryService;
import edu.osu.cse5234.model.Item;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class InventoryServiceBean
 */
@Stateless
@Remote(InventoryService.class)
public class InventoryServiceBean implements InventoryService {

    /**
     * Default constructor. 
     */
    public InventoryServiceBean() {
        // TODO Auto-generated constructor stub
    }
    
    public Inventory getAvailableInventory() {
    	
    	Inventory inventory = new Inventory();
    	List<Item> storeItems = new ArrayList<>();
    	
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
		
		inventory.setListofItems(storeItems);
		
		return inventory;
    }
    
	public boolean validateQuantity(List<Item> items) {
		return true;
	}
	public boolean updateInventory(List<Item> items) {
		return true;
	}

}
