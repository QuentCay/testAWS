package fr.seve.entities;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUser extends User {
    private final String slug; // L'attribut est final

    // Le constructeur s'assure que l'attribut est initialisé au moment de la création de l'objet
    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities, String slug) {
        super(username, password, authorities);
        this.slug = slug;
    }

	public String getSlug() {
		return slug;
	}

}

