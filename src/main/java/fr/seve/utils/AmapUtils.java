package fr.seve.utils;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import fr.seve.entities.AMAP;

public class AmapUtils {

    // Méthode utilitaire pour récupérer l'AMAP depuis la requête
    public static AMAP getAmapFromRequest(HttpServletRequest request) {
        AMAP amap = (AMAP) request.getAttribute("amap");
        if (amap == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AMAP not found");
        }
        return amap;
    }
}
