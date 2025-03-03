package fr.seve.entities;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import fr.seve.entities.enums.AmapUserRole;
import fr.seve.entities.enums.AmapUserType;

@Entity
public class AmapUser {


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @Column(nullable = false)
	@NotBlank
	@Size(min = 2, max = 50, message = "Le prénom doit contenir entre 2 et 50 caractères")
    private String firstname;

	@NotBlank(message = "Le nom est obligatoire")
	@Size(min = 2, max = 50, message = "Le nom doit contenir entre 2 et 50 caractères")
    @Column(nullable = false)
    private String name;

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
    
    @Column(nullable = false)
    private boolean contributionPaid = false;
    
    @Column(nullable = false)
    private String createDate;

    @Column(nullable = false)
    private String lastModifyDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AmapUserRole role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AmapUserType type;
    
    @OneToMany(mappedBy = "amapUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;

    // Relation avec AmapSpace
    @ManyToOne
    @JoinColumn(name = "amapSpace_id", nullable = false)
    private AmapSpace amapSpace;

    // Relations OneToOne avec les sous-types
    @OneToOne(mappedBy = "amapUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private AmapIndividualUser individualUser;

    @OneToOne(mappedBy = "amapUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private AmapWorksComitteeUser worksComitteeUser;

    @OneToOne(mappedBy = "amapUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private AmapProducerUser producerUser;

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhone() {
    	return phone;
    }
    
    public void setPhone(String phone) {
    	this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String string) {
        this.createDate = string;
    }

    public String getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(String string) {
        this.lastModifyDate = string;
    }

    public AmapUserRole getRole() {
        return role;
    }

    public void setRole(AmapUserRole role) {
        this.role = role;
    }

    public AmapUserType getType() {
        return type;
    }

    public void setType(AmapUserType type) {
        this.type = type;
    }

    public AmapSpace getAmapSpace() {
        return amapSpace;
    }

    public void setAmapSpace(AmapSpace amapSpace) {
        this.amapSpace = amapSpace;
    }

    public AmapIndividualUser getIndividualUser() {
        return individualUser;
    }

    public void setIndividualUser(AmapIndividualUser individualUser) {
        this.individualUser = individualUser;
    }

    public AmapWorksComitteeUser getWorksComitteeUser() {
        return worksComitteeUser;
    }

    public void setWorksComitteeUser(AmapWorksComitteeUser worksComitteeUser) {
    	this.worksComitteeUser = worksComitteeUser;
    }

    public AmapProducerUser getProducerUser() {
        return producerUser;
    }

    public void setProducerUser(AmapProducerUser producerUser) {
        this.producerUser = producerUser;
    }
    
    public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
    public String toString() {
    	return "AmapUser [id=" + id + ", firstname=" + firstname + ", name=" + name + ", email=" + email + ", password="
    			+ password + ", phone=" + phone + ", createDate=" + createDate + ", lastModifyDate=" + lastModifyDate
    			+ ", role=" + role + ", type=" + type + ", amapSpace=" + amapSpace + ", individualUser="
    			+ individualUser + ", worksComitteeUser=" + worksComitteeUser + ", producerUser=" + producerUser + "]";
    }

	public boolean isContributionPaid() {
		return contributionPaid;
	}

	public void setContributionPaid(boolean contributionPaid) {
		this.contributionPaid = contributionPaid;
	}
}
