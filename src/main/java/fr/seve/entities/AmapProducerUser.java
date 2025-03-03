package fr.seve.entities;

import java.util.List;

import javax.persistence.*;

@Entity
public class AmapProducerUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private AmapUser amapUser;

    @Column(nullable = false)
    private String rib;

    @Column(nullable = false)
    private String address;
    
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

    public AmapUser getAmapUser() {
        return amapUser;
    }

    public void setAmapUser(AmapUser amapUser) {
        this.amapUser = amapUser;
    }

    public String getRib() {
        return rib;
    }

    public void setRib(String rib) {
        this.rib = rib;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
