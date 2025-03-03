package fr.seve.entities;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.*;

import fr.seve.entities.enums.SaasUserLevel;

@Entity
@Table(name = "saasUsers")
public class SaasUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@NotBlank(message = "Le nom est obligatoire")
	@Size(min = 2, max = 50, message = "Le nom doit contenir entre 2 et 50 caractères")
	private String name;

	@Column(nullable = false)
	@NotBlank
	@Size(min = 2, max = 50, message = "Le prénom doit contenir entre 2 et 50 caractères")
	private String firstname;

	@Column(nullable = false, unique = true)
	@NotBlank
	@Email(message = "L''adresse email n''est pas valide")
	private String email;

	@Column(nullable = false)
	@NotBlank(message = "Le mot de passe est obligatoire")
	@Size(min = 8, message = "Le mot de passe doit contenir au moins 8 caractères")
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$", message = "Le mot de passe doit contenir au moins une majuscule, une minuscule et un chiffre")
	private String password;

	@Column(nullable = false)
	@NotBlank(message = "Le numéro de téléphone est obligatoire")
	@Pattern(regexp = "^\\d{10}$", message = "Le numéro de téléphone doit contenir exactement 10 chiffres")
	private String phone;


	@Column
	private String createDate;

	@Column
	private String lastModifyDate;

	@Column
	private SaasUserLevel saasUserLevel;

	// Relation avec Subscription
	@ManyToOne
	@JoinColumn(name = "subscription_id")
	private Subscription subscription;


	// Relation avec AMAP
	@OneToOne
	@JoinColumn(name = "amap_id")
	private AMAP amap;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getPhone() {
		return phone;
	}

	public String getCreateDate() {
		return createDate;
	}

	public String getLastModifyDate() {
		return lastModifyDate;
	}

	public SaasUserLevel getSaasUserLevel() {
		return saasUserLevel;
	}

	public Subscription getSubscription() {
		return subscription;
	}


	public AMAP getAmap() {
		return amap;
	}

	public void setAmap(AMAP amap) {
		this.amap = amap;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

	public void setSaasUserLevel(SaasUserLevel saasUserLevel) {
		this.saasUserLevel = saasUserLevel;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setCreateDate(String localDate) {
		this.createDate = localDate;
	}

	public void setLastModifyDate(String lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}

}
