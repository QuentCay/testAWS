package fr.seve.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="addresses")
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@Column
	private String number; 
	
	@Column
	private String streetName; 
	
	@Column
	private String postalCode; 
	
	@Column
	private String town;
	
//	@ManyToOne
//	@JoinColumn(name="producer_id", nullable = true)
//	private Producer producer; 

	public Long getId() {
		return id;
	}

	public String getNumber() {
		return number;
	}

	public String getStreetName() {
		return streetName;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public String getTown() {
		return town;
	}

//	public Producer getProducer() {
//		return producer;
//	}
//
//	public void setProducer(Producer producer) {
//		this.producer = producer;
//	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public void setTown(String town) {
		this.town = town;
	} 

}
