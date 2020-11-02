package edu.osu.cse5234.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import edu.osu.cse5234.model.Item;

@Entity
@Table(name="ITEM")
public class Item implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1268965135247453469L;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="UNIT_PRICE")
	private double price;
	
	@Column(name="AVAILABLE_QUANTITY")
	private int quantity;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@Column(name="ITEM_NUMBER")
	private int itemNumber;
	
	@Column(name="DESCRIPTION")
	private String description;	
	
	public Item() {
		
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getItemNumber() {
		return itemNumber;
	}
	
	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
