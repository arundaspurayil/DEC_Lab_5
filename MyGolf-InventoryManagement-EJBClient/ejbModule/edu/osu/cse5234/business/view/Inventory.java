package edu.osu.cse5234.business.view;


import java.io.Serializable;
import java.util.List;

import edu.osu.cse5234.model.Item;

public class Inventory implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2355044822554432758L;
	List<Item> listofItems;
	
	public List<Item> getListofItems() {
		return listofItems;
	}
	public void setListofItems(List<Item> listofItems) {
		this.listofItems = listofItems;
	}

	
	
}
