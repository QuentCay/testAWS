package fr.seve.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.seve.entities.AMAP;
import fr.seve.entities.AmapProducerUser;
import fr.seve.entities.AmapSpace;
import fr.seve.entities.AmapUser;
import fr.seve.entities.Box;
import fr.seve.entities.Product;
import fr.seve.service.AmapService;
import fr.seve.service.AmapSpaceService;
import fr.seve.service.ProductService;

@Controller
@RequestMapping("/{slug}/product")
public class ProductController {

	@Autowired
	private ProductService productService; 
	
	@Autowired
	private AmapService amapService;
	
	@Autowired
	private AmapSpaceService amapSpaceService;
	
	@GetMapping
	public ModelAndView listProducts(@PathVariable String slug, Model model) {
		AMAP amap = amapService.findBySlug(slug);
        if (amap == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AMAP not found");
        }
        
        Long amapSpaceId = amap.getId();
        
		List<Product> products = productService.findByAmapSpaceId(amapSpaceId);
		model.addAttribute("products", products);
		ModelAndView mv = new ModelAndView("product-list");
		mv.addObject("css", "/resources/css/amap/boxList.css");
	    return mv;
	}

	
	@Secured({"ROLE_AMAP_ADMIN","ROLE_AMAP_SUPERVISOR"})
	@GetMapping("/admin")
	public ModelAndView adminListProducts(@PathVariable String slug, Model model) {
		AMAP amap = amapService.findBySlug(slug);
        if (amap == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AMAP not found");
        }
        
        Long amapSpaceId = amap.getId();
        
		List<Product> products = productService.findByAmapSpaceId(amapSpaceId);
		model.addAttribute("products", products);
		ModelAndView mv = new ModelAndView("admin-product-list");
		mv.addObject("css", "/resources/css/amap/boForms.css");
	    return mv;
	}	
	
	
	@GetMapping("{id}")
	public String getProduct(@PathVariable String slug, @PathVariable Long id, Model model) {
		AMAP amap = amapService.findBySlug(slug);
        if (amap == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AMAP not found");
        }

		Product product = productService.findById(id);
		model.addAttribute("product", product);
		return "product-details";
	}
	
	@GetMapping("/add")
	public String showAddForm(@PathVariable String slug, Model model) {
		AMAP amap = amapService.findBySlug(slug);
        if (amap == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AMAP not found");
        }
        
		model.addAttribute("categories", Product.Category.values());
		model.addAttribute("product", new Product());
		return "product-form";
	}
	
	@PostMapping("add")
	public String saveProduct(@PathVariable String slug, @ModelAttribute Product product, 
			@ModelAttribute("amapUser") AmapUser amapUser,
			@RequestParam("image") MultipartFile image, RedirectAttributes redirectAttributes) {
		AMAP amap = amapService.findBySlug(slug);
        if (amap == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AMAP not found");
        }
        
        AmapSpace amapSpace = amapSpaceService.findById(amap.getId());
        
        AmapProducerUser aPU = amapUser.getProducerUser();
        
        if (image != null && !image.isEmpty()) {
			try {
				product.setImageData(image.getBytes());
			} catch (IOException e) {
				redirectAttributes.addFlashAttribute("message", "Erreur lors de l'importation de l'image.");
				e.printStackTrace();
				return "redirect:/{slug}/product/admin";
			}
		}
        
		product.setCreationDate(LocalDate.now());
		product.setAmapSpace(amapSpace);
		product.setAmapProducerUser(aPU);
		productService.save(product);
		return "redirect:/{slug}/product/admin";
	}
	
	@GetMapping("/addProd")
	public String showAddFormProducer(@PathVariable String slug, Model model) {
		AMAP amap = amapService.findBySlug(slug);
        if (amap == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AMAP not found");
        }
        
		model.addAttribute("categories", Product.Category.values());
		model.addAttribute("product", new Product());
		return "product-form-prod";
	}
	
	@PostMapping("addProd")
	public String saveProductProducer(@PathVariable String slug, @ModelAttribute Product product, 
			@ModelAttribute("amapUser") AmapUser amapUser,
			@RequestParam("image") MultipartFile image, RedirectAttributes redirectAttributes) {
		AMAP amap = amapService.findBySlug(slug);
        if (amap == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AMAP not found");
        }
        
        AmapSpace amapSpace = amapSpaceService.findById(amap.getId());
        
        AmapProducerUser aPU = amapUser.getProducerUser();
        
        if (image != null && !image.isEmpty()) {
			try {
				product.setImageData(image.getBytes());
			} catch (IOException e) {
				redirectAttributes.addFlashAttribute("message", "Erreur lors de l'importation de l'image.");
				e.printStackTrace();
				return "redirect:/{slug}/myproducts";
			}
		}
        
		product.setCreationDate(LocalDate.now());
		product.setAmapSpace(amapSpace);
		product.setAmapProducerUser(aPU);
		productService.save(product);
		return "redirect:/{slug}/myproducts";
	}

	@GetMapping("/delete/{id}")
	public String deleteProduct(@PathVariable String slug, @PathVariable Long id) {
		AMAP amap = amapService.findBySlug(slug);
        if (amap == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AMAP not found");
        }
        
		productService.deleteById(id);
		return "redirect:/{slug}/product/admin";
	}
	
	@GetMapping("/deleteProd/{id}")
	public String deleteProductProducer(@PathVariable String slug, @PathVariable Long id) {
		AMAP amap = amapService.findBySlug(slug);
        if (amap == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AMAP not found");
        }
        
		productService.deleteById(id);
		return "redirect:/{slug}/myproducts";
	}

	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable String slug, @PathVariable Long id, Model model) {
		AMAP amap = amapService.findBySlug(slug);
        if (amap == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AMAP not found");
        }
        
		Product product = productService.findById(id);
		model.addAttribute("categories", Product.Category.values());
		model.addAttribute("product", product);
		return "product-form";
	}
	
	@PostMapping("/edit/{id}")
	public String updateProduct(@PathVariable String slug, @PathVariable Long id, @ModelAttribute Product product,
			@ModelAttribute("amapUser") AmapUser amapUser,
			@RequestParam("image") MultipartFile image, RedirectAttributes redirectAttributes) {
		AMAP amap = amapService.findBySlug(slug);
        if (amap == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AMAP not found");
        }
        
		Product oldProduct = productService.findById(id);
		AmapSpace amapSpace = amapSpaceService.findById(amap.getId());
		AmapProducerUser aPU = amapUser.getProducerUser();
		
		product.setCreationDate(oldProduct.getCreationDate());
		product.setLastModifiedDate(LocalDate.now());
		product.setAmapSpace(amapSpace);
		product.setAmapProducerUser(aPU);
		if (oldProduct.getImageData() != null) {
			product.setImageData(oldProduct.getImageData());
		}
		
		if (image != null && !image.isEmpty()) {
			try {
				product.setImageData(image.getBytes());
			} catch (IOException e) {
				redirectAttributes.addFlashAttribute("message", "Erreur lors de l'importation de l'image.");
				e.printStackTrace();
				return "redirect:/{slug}/product/admin";
			}
		}
		productService.save(product);
		return "redirect:/{slug}/product/admin";
	}
	
	@GetMapping("/editProd/{id}")
	public String showEditFormProducer(@PathVariable String slug, @PathVariable Long id, Model model) {
		AMAP amap = amapService.findBySlug(slug);
        if (amap == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AMAP not found");
        }
        
		Product product = productService.findById(id);
		model.addAttribute("categories", Product.Category.values());
		model.addAttribute("product", product);
		return "product-form-prod";
	}
	
	@PostMapping("/editProd/{id}")
	public String updateProductProducer(@PathVariable String slug, @PathVariable Long id, @ModelAttribute Product product,
			@ModelAttribute("amapUser") AmapUser amapUser,
			@RequestParam("image") MultipartFile image, RedirectAttributes redirectAttributes) {
		AMAP amap = amapService.findBySlug(slug);
        if (amap == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AMAP not found");
        }
        
		Product oldProduct = productService.findById(id);
		AmapSpace amapSpace = amapSpaceService.findById(amap.getId());
		AmapProducerUser aPU = amapUser.getProducerUser();
		
		product.setCreationDate(oldProduct.getCreationDate());
		product.setLastModifiedDate(LocalDate.now());
		product.setAmapSpace(amapSpace);
		product.setAmapProducerUser(aPU);
		if (oldProduct.getImageData() != null) {
			product.setImageData(oldProduct.getImageData());
		}
		
		if (image != null && !image.isEmpty()) {
			try {
				product.setImageData(image.getBytes());
			} catch (IOException e) {
				redirectAttributes.addFlashAttribute("message", "Erreur lors de l'importation de l'image.");
				e.printStackTrace();
				return "redirect:/{slug}/myproducts";
			}
		}
		productService.save(product);
		return "redirect:/{slug}/myproducts";
	}
	
	@GetMapping("/image/{id}")
	public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
	    Product product = productService.findById(id);
	    if (product == null) {
	        System.out.println("Aucun panier trouvé avec l'ID : " + id);
	        return ResponseEntity.notFound().build();
	    }

	    byte[] imageData = product.getImageData();
	    if (imageData != null) {
	        System.out.println("Image trouvée pour l'ID : " + id);
	        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imageData);
	    }

	    System.out.println("Pas d'image pour l'ID : " + id);
	    return ResponseEntity.notFound().build();
	}
	
}
