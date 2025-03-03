package fr.seve.entities;

import javax.persistence.*;

@Entity
public class AmapIndividualUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private AmapUser amapUser;


	@Column(nullable = false)
    private boolean volunteer;

    public boolean isVolunteer() {
		return volunteer;
	}

	public void setVolunteer(boolean volunteer) {
		this.volunteer = volunteer;
	}

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

	@Override
	public String toString() {
		return "AmapIndividualUser [id=" + id + ", amapUser=" + amapUser.toString() + ", volunteer=" + volunteer + "]";
	}


}
