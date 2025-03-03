package fr.seve.entities;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "amap_spaces")
public class AmapSpace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "amap_id", nullable = false)
    private AMAP amap;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "configuration_id", nullable = false)
    private Configuration configuration;
    
	@ManyToOne
	@JoinColumn(name = "subscription_id")
	private Subscription subscription;
	
    @OneToMany(mappedBy = "amapSpace", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AmapUser> amapUsers;

    @OneToMany(mappedBy = "amapSpace", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Activity> activities;
    
    @OneToMany(mappedBy = "amapSpace", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;
    
    @OneToMany(mappedBy = "amapSpace", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Box> boxes;
    
	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AMAP getAmap() {
        return amap;
    }

    public void setAmap(AMAP amap) {
        this.amap = amap;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

	public Subscription getSubscription() {
		return subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

	public List<AmapUser> getAmapUsers() {
		return amapUsers;
	}
	
	public void setAmapUsers(List<AmapUser> amapUsers) {
		this.amapUsers = amapUsers;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<Box> getBoxes() {
		return boxes;
	}

	public void setBoxes(List<Box> boxes) {
		this.boxes = boxes;
	}
}
