package fr.seve.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "cart_items")
public class CartItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "cart_id", nullable = false)
	private Cart cart;

	@ManyToOne
	@JoinColumn(name = "box_id", nullable = true)
	private Box box;

	@ManyToOne
	@JoinColumn(name = "activity_id", nullable = true)
	private Activity activity;

	@ManyToOne
	@JoinColumn(name = "product_id", nullable = true)
	private Product product;
	
	private int quantity;
	
	private double price;
	
	private String genre; 
	
	private String name;

	public CartItem() {
	}

	public Long getId() {
		return id;
	}

	public Cart getCart() {
		return cart;
	}

	public Box getBox() {
		return box;
	}

	public Activity getActivity() {
		return activity;
	}

	public Product getProduct() {
		return product;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public void setBox(Box box) {
		this.box = box;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;

	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getTotalPrice() {
		double price = 0;
		if (box != null) {
			price = box.getPrice();
		} else if (product != null) {
			price = product.getPrice();
		} else if (activity != null) {
			price = activity.getPrice();
		} else {
			price = this.getPrice();
		}
		return price * quantity;
	}
}
