package fr.seve.entities;

import javax.persistence.*;

@Entity
public class AmapWorksComitteeUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private AmapUser amapUser;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false, unique = true)
    private String siret;

    // Getters, setters, et constructeur
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }
}
