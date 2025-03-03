package fr.seve.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "carts")
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
	private List<CartItem> items = new ArrayList<>();

	
	
	
	public Cart() {
	}

	public void addItem(CartItem item) {
		items.add(item);
	}

	public void updateQuantity(Long itemId, int quantity, String genre) {
		for (CartItem item : items) {
			
			
				//Gérer le cas de box
				if (genre.equals("BOX") && item.getBox() !=null && item.getBox().getId().equals(itemId)) {
					
					if (quantity <= 0) {
						items.remove(item); 
					} else {
						item.setQuantity(quantity); 
					}
					break;
				
			} else if (genre.equals("ACTIVITY") && item.getActivity() !=null && item.getActivity().getId().equals(itemId)) {
					
					if (quantity <= 0) {
						items.remove(item); 
					} else {
						item.setQuantity(quantity); 
					}
				
					break;
			} else if (genre.equals("PRODUCT") & item.getProduct() !=null && item.getProduct().getId().equals(itemId)) {
					
					if (quantity <= 0) {
						items.remove(item); 
					} else {
						item.setQuantity(quantity); 
					}
					break;
				}
				
			}
				
			
			
		}
	

	public void removeItem(Long itemId, String genre) {
		if ("BOX".equals(genre)) {
			CartItem myItem = null;
			for (int i = 0; i < items.size(); i++) {
				if (items.get(i) != null && items.get(i).getId() == itemId) {
					myItem = items.get(i); 

					break;
				}
			}
			items.remove(myItem);
		} else if ("ACTIVITY".equals(genre)) { 
			CartItem myItem = null;
			for (int i = 0; i < items.size(); i++) {
				if (items.get(i) != null && items.get(i).getId() == itemId) {
					myItem = items.get(i); 

					break;
				}
			}
			items.remove(myItem);
			
		} else {
			CartItem myItem = null;
			for (int i = 0; i < items.size(); i++) {
				if (items.get(i) != null && items.get(i).getId() == itemId) {
					myItem = items.get(i); 

					break;
				}
			}
			items.remove(myItem);
			
		}
		
	}

	public void showCart() {
		for (CartItem cartItem : items) {
			System.out.println(cartItem);
		}
	}

	public double getTotalPrice() {
		double total = 0;
		for (CartItem cartItem : items) {
			total += cartItem.getTotalPrice();
		}
		return total;
	}

	public Long getId() {
		return id;
	}

	public List<CartItem> getItems() {
		return items;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setItems(List<CartItem> items) {
		this.items = items;
	}
	
	
}
