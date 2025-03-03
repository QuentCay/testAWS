package fr.seve.entities;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="subscription")
public class Subscription {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String name;
	@Column
	private String price;
	@Column
	private String description;
	@Column
	private String subscribeDate;
	@Column
	private SubStatus substatus;
	
	// Relation avec Function
	@ManyToMany
	@JoinTable( name= "subscription_functions",
		joinColumns = @JoinColumn (name="subscription_id"),
		inverseJoinColumns = @JoinColumn(name="functions_id")
			)
	private List<Function> functionsList = new ArrayList<>();
	
	//Relation avec SaasUser
	@OneToMany(mappedBy = "subscription", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SaasUser> saasUserList = new ArrayList<>();
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPrice() {
		return price;
	}

	public String getDescription() {
		return description;
	}

	
	public String getSubscribeDate() {
		return subscribeDate;
	}

	public SubStatus getSubstatus() {
		return substatus;
	}

	
	public List<Function> getFunctionsList() {
		return functionsList;
	}

	
	
	public List<SaasUser> getSaasUserList() {
		return saasUserList;
	}

	public void setSaasUserList(List<SaasUser> saasUserList) {
		this.saasUserList = saasUserList;
	}

	public void setFunctionsList(List<Function> functionsList) {
		this.functionsList = functionsList;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	
	public void setSubscribeDate(String subscribeDate) {
		this.subscribeDate = subscribeDate;
	}

	public void setSubstatus(SubStatus substatus) {
		this.substatus = substatus;
	}
}
