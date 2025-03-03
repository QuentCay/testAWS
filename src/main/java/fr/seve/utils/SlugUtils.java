package fr.seve.utils;

public class SlugUtils {

	public static String toSlug(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        return input.toLowerCase()
                    .replaceAll("[^a-z0-9]+", "-") // Remplace les caractères non alphanumériques par des tirets
                    .replaceAll("^-|-$", "");     // Supprime les tirets en début/fin
    }
}
