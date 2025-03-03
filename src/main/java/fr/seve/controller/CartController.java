package fr.seve.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import fr.seve.entities.AMAP;
import fr.seve.entities.Activity;
import fr.seve.entities.AmapUser;
import fr.seve.entities.Box;
import fr.seve.entities.Cart;
import fr.seve.entities.CartItem;
import fr.seve.entities.Product;
import fr.seve.repository.CartRepository;
import fr.seve.service.ActivityService;
import fr.seve.service.AmapService;
import fr.seve.service.BoxService;
import fr.seve.service.ProductService;

@Secured({"ROLE_AMAP_USER","ROLE_AMAP_ADMIN","ROLE_AMAP_SUPERVISOR"})
@Controller
@RequestMapping("/{slug}/cart")
public class CartController {

	@Autowired
	private BoxService boxService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private AmapService amapService;
	
	@Autowired
    private CartRepository cartRepository; 

	@GetMapping
	public String viewCart(@PathVariable String slug, HttpSession session, Model model, @ModelAttribute("amapUser") AmapUser amapUser) {
	    AMAP amap = amapService.findBySlug(slug);
	    if (amap == null) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AMAP not found");
	    }

	    Cart cart = (Cart) session.getAttribute("cart");
	    if (cart == null) {
	        cart = new Cart();
	        session.setAttribute("cart", cart);
	    }

	    // Vérifier et ajouter les frais d'adhésion si nécessaire
	    if (addContributionToCart(cart, amap, amapUser)) {
	        model.addAttribute("error", "Les frais d'adhésion à l'AMAP ont été ajoutés au panier.");
	    }

	    model.addAttribute("cart", cart);
	    return "cart";
	}


	@PostMapping("add")
	public String addToCart(@PathVariable String slug, 
			@RequestParam("boxId") Long boxId, 
			@RequestParam("genre") String genre, 
			HttpSession session) {
		System.out.println("Genre: " + genre);
		AMAP amap = amapService.findBySlug(slug);
        if (amap == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AMAP not found");
        }
        
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}

		
		if (boxId != null) {
            Box box = boxService.findById(boxId);
            if (box != null) {
                CartItem cartItem = new CartItem();
                cartItem.setBox(box);
                cartItem.setCart(cart);
                cartItem.setPrice(box.getPrice());
                cartItem.setGenre(genre);
                cartItem.setQuantity(1);  // Quantité par défaut
                cart.addItem(cartItem);
            }
        }
		
	        cartRepository.save(cart);
	        return "redirect:/{slug}/cart";  // Redirection vers la page du panier
	    }
	
	
	@PostMapping("add/product")
	public String addProductToCart(@PathVariable String slug, 
			@RequestParam("productId") Long productId,
			@RequestParam("genre") String genre, 
			HttpSession session) {
		AMAP amap = amapService.findBySlug(slug);
        if (amap == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AMAP not found");
        }
        
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
	
		  if (productId != null) {
	            Product product = productService.findById(productId);
	            if (product != null) {
	                CartItem cartItem = new CartItem();
	                cartItem.setProduct(product);
	                cartItem.setCart(cart);
	                cartItem.setPrice(product.getPrice());
	                cartItem.setGenre(genre);
	                cartItem.setQuantity(1);  // Quantité par défaut
	                cart.addItem(cartItem);
	            }
	        }

	        // Sauvegarder les modifications du panier
	        cartRepository.save(cart);
	        return "redirect:/{slug}/cart";  // Redirection vers la page du panier
	    }
	
	@PostMapping("add/activity")
	public String addActivityToCart(@PathVariable String slug, 
			@RequestParam("activityId") Long activityId, 
			@RequestParam("genre") String genre, 
			HttpSession session) {
		AMAP amap = amapService.findBySlug(slug);
        if (amap == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AMAP not found");
        }
        
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		
	        if (activityId != null) {
	            Activity activity = activityService.findById(activityId);
	            if (activity != null) {
	                CartItem cartItem = new CartItem();
	                cartItem.setActivity(activity);
	                cartItem.setCart(cart);
	                cartItem.setPrice(activity.getPrice());
	                cartItem.setGenre(genre);
	                cartItem.setQuantity(1);  // Quantité par défaut
	                cart.addItem(cartItem);
	            }
	        }
	        

	        // Sauvegarder les modifications du panier
	        cartRepository.save(cart);

	        return "redirect:/{slug}/cart";  // Redirection vers la page du panier
	    }

	
	@PostMapping("clear")
	public String clearCart(@PathVariable String slug, HttpSession session) {
		AMAP amap = amapService.findBySlug(slug);
        if (amap == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AMAP not found");
        }
        session.removeAttribute("cart");
	    return "redirect:/{slug}/cart";
	}
	
	@PostMapping("update")
	public String updateQuantity(@PathVariable String slug, 
								 @RequestParam("itemId") Long itemId, 
	                             @RequestParam("quantity") int quantity, 
	                             @RequestParam("genre") String genre, 
	                             HttpSession session) {
		AMAP amap = amapService.findBySlug(slug);
        if (amap == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AMAP not found");
        }
        Cart cart = (Cart) session.getAttribute("cart");
	    if (cart != null) {
	        cart.updateQuantity(itemId, quantity, genre);
	    }
	    
	    cartRepository.save(cart);
		return "redirect:/{slug}/cart";
	}

	@PostMapping("update/product")
	public String updateProductQuantity(@PathVariable String slug, 
								 @RequestParam("productId") Long productId, 
	                             @RequestParam("quantity") int quantity, 
	                             @RequestParam("genre") String genre, 
	                             HttpSession session) {
		AMAP amap = amapService.findBySlug(slug);
		if (amap == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AMAP not found");
        }
        
	    Cart cart = (Cart) session.getAttribute("cart");
	    if (cart != null) {
	        cart.updateQuantity(productId, quantity, genre);
	    }
	    cartRepository.save(cart);
	    return "redirect:/{slug}/cart";
	}
	
	@PostMapping("update/activity")
	public String updateActivityQuantity(@PathVariable String slug, 
								 @RequestParam("activityId") Long activityId, 
	                             @RequestParam("quantity") int quantity, 
	                             @RequestParam("genre") String genre, 
	                             HttpSession session) {
		AMAP amap = amapService.findBySlug(slug);
		if (amap == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AMAP not found");
        }
        
	    Cart cart = (Cart) session.getAttribute("cart");
	    if (cart != null) {
	        cart.updateQuantity(activityId, quantity, genre);
	    }
	    cartRepository.save(cart);
	    return "redirect:/{slug}/cart";
	}

	
	
	@PostMapping("remove")
	public String removeItem(@PathVariable String slug, 
			@RequestParam("itemId") Long itemId,
			@RequestParam("genre") String genre,
			HttpSession session) {
		AMAP amap = amapService.findBySlug(slug);
        if (amap == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AMAP not found");
        }
        
	    Cart cart = (Cart) session.getAttribute("cart");
	    if (cart != null) {
	        cart.removeItem(itemId, genre);
	    }
	    cartRepository.save(cart);
	    session.setAttribute("cart", cart);
	    return "redirect:/{slug}/cart";
	}

	private boolean addContributionToCart(Cart cart, AMAP amap, AmapUser amapUser) {
	    // Si l'utilisateur a déjà payé sa contribution, ne rien faire
	    if (amapUser.isContributionPaid()) {
	        return false;
	    }

	    // Vérifier que le panier n'est pas null ou vide
	    if (cart == null || cart.getItems().isEmpty()) {
	        return false;
	    }

	    // Nom de l'article pour les frais d'adhésion
	    String membershipFeeName = "Frais d'adhésion à l'AMAP " + amap.getName();

	    // Rechercher l'article existant
	    Optional<CartItem> optionalMembershipFeeItem = cart.getItems().stream()
	            .filter(item -> membershipFeeName.equals(item.getName()))
	            .findFirst();

	    if (optionalMembershipFeeItem.isPresent()) {
	        // L'article existe, vérifier et ajuster la quantité si nécessaire
	        CartItem membershipFeeItem = optionalMembershipFeeItem.get();
	        if (membershipFeeItem.getQuantity() != 1) {
	            membershipFeeItem.setQuantity(1); // Réinitialiser la quantité
	            cartRepository.save(cart);       // Sauvegarder uniquement si modifié
	        }
	        return false; // Aucun nouvel article ajouté
	    }

	    // Si l'article n'existe pas, le créer et l'ajouter au panier
	    CartItem membershipFeeItem = new CartItem();
	    membershipFeeItem.setName("Frais d'adhésion à l'AMAP " + amap.getName());
	    membershipFeeItem.setPrice(amap.getMembershipFee());
	    membershipFeeItem.setQuantity(1);
	    membershipFeeItem.setGenre("Cotisation annuelle");
	    membershipFeeItem.setCart(cart);

	    cart.addItem(membershipFeeItem); // Ajouter au panier
	    cartRepository.save(cart);       // Sauvegarder le panier

	    return true; // Nouvel article ajouté
	}

}

	
   
    
    



