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
import fr.seve.entities.Configuration;
import fr.seve.service.AmapProducerUserService;
import fr.seve.service.AmapService;
import fr.seve.service.AmapSpaceService;
import fr.seve.service.BoxService;

@Controller
@RequestMapping("/{slug}/box")
public class BoxController {

	@Autowired
	private BoxService boxService;
	
	@Autowired
	private AmapService amapService;
	
	@Autowired
	private AmapSpaceService amapSpaceService;
	
	@Autowired
	private AmapProducerUserService amapProducerUserService;
	
    @GetMapping
    public ModelAndView listBoxes(@PathVariable String slug, Model model) {
        AMAP amap = amapService.findBySlug(slug);
        if (amap == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AMAP not found");
        }
        
        Long amapSpaceId = amap.getId();

        List<Box> boxes = boxService.findByAmapSpaceId(amapSpaceId);
        model.addAttribute("boxes", boxes);
        model.addAttribute("amap", amap);
        model.addAttribute("slug", slug);
        ModelAndView mv = new ModelAndView("box-list");
        mv.addObject("css", "/resources/css/amap/boxList.css");
        return mv;
    }
    
    @Secured({"ROLE_AMAP_ADMIN","ROLE_AMAP_SUPERVISOR"})
	@GetMapping("/admin")
	public ModelAndView adminListBoxes(@PathVariable String slug, Model model) {
		AMAP amap = amapService.findBySlug(slug);
        if (amap == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AMAP not found");
        }

        Long amapSpaceId = amap.getId();
        
		List<Box> boxes = boxService.findByAmapSpaceId(amapSpaceId);
		model.addAttribute("boxes", boxes);
		model.addAttribute("slug", slug);
		ModelAndView mv = new ModelAndView("admin-box-list");
		mv.addObject("css", "/resources/css/amap/boForms.css");
	    return mv;
	}	

	@GetMapping("{id}")
	public String getBox(@PathVariable String slug, @PathVariable Long id, Model model) {
		AMAP amap = amapService.findBySlug(slug);
        if (amap == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AMAP not found");
        }

		Box box = boxService.findById(id);
		model.addAttribute("box", box);
		return "box-details";
	}

	@GetMapping("/add")
	public String showAddForm(@PathVariable String slug, Model model) {
		AMAP amap = amapService.findBySlug(slug);
        if (amap == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AMAP not found");
        }
        
		model.addAttribute("categories", Box.Category.values());
		model.addAttribute("frequencies", Box.Frequency.values());
		model.addAttribute("box", new Box());
		return "box-form";
	}

	@PostMapping("add")
	public String saveBox(@PathVariable String slug, @ModelAttribute Box box,
			@ModelAttribute("amapUser") AmapUser amapUser,
			@RequestParam("image") MultipartFile image, RedirectAttributes redirectAttributes) {
		
		AMAP amap = amapService.findBySlug(slug);
        if (amap == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AMAP not found");
        }
        
        AmapProducerUser aPU = amapUser.getProducerUser();
        
        
        if (image != null && !image.isEmpty()) {
			try {
				box.setImageData(image.getBytes());
			} catch (IOException e) {
				redirectAttributes.addFlashAttribute("message", "Erreur lors de l'importation de l'image.");
				e.printStackTrace();
				return "redirect:/{slug}/box/admin";
			}
		}
        
		box.setCreationDate(LocalDate.now());
		box.setAmapSpace(amap.getAmapSpace());
		box.setAmapProducerUser(aPU);
		boxService.save(box);
		return "redirect:/{slug}/box/admin";
	}

	@GetMapping("/addProd")
	public String showAddFormProducer(@PathVariable String slug, Model model) {
		AMAP amap = amapService.findBySlug(slug);
        if (amap == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AMAP not found");
        }
        
		model.addAttribute("categories", Box.Category.values());
		model.addAttribute("frequencies", Box.Frequency.values());
		model.addAttribute("box", new Box());
		return "box-form-prod";
	}

	@PostMapping("addProd")
	public String saveBoxProducer(@PathVariable String slug, @ModelAttribute Box box,
			@ModelAttribute("amapUser") AmapUser amapUser,
			@RequestParam("image") MultipartFile image, RedirectAttributes redirectAttributes) {
		
		AMAP amap = amapService.findBySlug(slug);
        if (amap == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AMAP not found");
        }
        
        AmapProducerUser aPU = amapUser.getProducerUser();
        
        
        if (image != null && !image.isEmpty()) {
			try {
				box.setImageData(image.getBytes());
			} catch (IOException e) {
				redirectAttributes.addFlashAttribute("message", "Erreur lors de l'importation de l'image.");
				e.printStackTrace();
				return "redirect:/{slug}/myproducts";
			}
		}
        
		box.setCreationDate(LocalDate.now());
		box.setAmapSpace(amap.getAmapSpace());
		box.setAmapProducerUser(aPU);
		boxService.save(box);
		return "redirect:/{slug}/myproducts";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteBox(@PathVariable String slug, @PathVariable Long id) {
		AMAP amap = amapService.findBySlug(slug);
        if (amap == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AMAP not found");
        }
        
		boxService.deleteById(id);
		return "redirect:/{slug}/box/admin";
	}
	
	@GetMapping("/deleteProd/{id}")
	public String deleteBoxProducer(@PathVariable String slug, @PathVariable Long id) {
		AMAP amap = amapService.findBySlug(slug);
        if (amap == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AMAP not found");
        }
        
		boxService.deleteById(id);
		return "redirect:/{slug}/myproducts";
	}
	
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String slug, @PathVariable Long id, Model model) {
    	AMAP amap = amapService.findBySlug(slug);
        if (amap == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AMAP not found");
        }
        
        Box box = boxService.findById(id);
        model.addAttribute("categories", Box.Category.values());
        model.addAttribute("frequencies", Box.Frequency.values());
        model.addAttribute("box", box);
        return "box-form";
    }

	@PostMapping("/edit/{id}")
	public String updateBox(@PathVariable String slug, @PathVariable Long id, @ModelAttribute Box box,
			@ModelAttribute("amapUser") AmapUser amapUser,
			@RequestParam("image") MultipartFile image, RedirectAttributes redirectAttributes) {
		AMAP amap = amapService.findBySlug(slug);
        if (amap == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AMAP not found");
        }
        
		Box oldBox = boxService.findById(id);
		AmapSpace amapSpace = amapSpaceService.findById(amap.getId());
		AmapProducerUser aPU = amapUser.getProducerUser();
		
		box.setCreationDate(oldBox.getCreationDate());
		box.setLastModifiedDate(LocalDate.now());
		box.setAmapSpace(amapSpace);
		box.setAmapProducerUser(aPU);
		if (oldBox.getImageData() != null) {
			box.setImageData(oldBox.getImageData());
		} 
		if (image != null && !image.isEmpty()) {
			try {
				box.setImageData(image.getBytes());
			} catch (IOException e) {
				redirectAttributes.addFlashAttribute("message", "Erreur lors de l'importation de l'image.");
				e.printStackTrace();
				return "redirect:/{slug}/box/admin";
				}
			} 
		
    	boxService.save(box);
        return "redirect:/{slug}/box/admin";
    }
	
	@GetMapping("/editProd/{id}")
    public String showEditFormProducer(@PathVariable String slug, @PathVariable Long id, Model model) {
    	AMAP amap = amapService.findBySlug(slug);
        if (amap == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AMAP not found");
        }
        
        Box box = boxService.findById(id);
        model.addAttribute("categories", Box.Category.values());
        model.addAttribute("frequencies", Box.Frequency.values());
        model.addAttribute("box", box);
        return "box-form-prod";
    }

	@PostMapping("/editProd/{id}")
	public String updateBoxProducer(@PathVariable String slug, @PathVariable Long id, @ModelAttribute Box box,
			@ModelAttribute("amapUser") AmapUser amapUser,
			@RequestParam("image") MultipartFile image, RedirectAttributes redirectAttributes) {
		AMAP amap = amapService.findBySlug(slug);
        if (amap == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AMAP not found");
        }
        
		Box oldBox = boxService.findById(id);
		AmapSpace amapSpace = amapSpaceService.findById(amap.getId());
		AmapProducerUser aPU = amapUser.getProducerUser();
		
		box.setCreationDate(oldBox.getCreationDate());
		box.setLastModifiedDate(LocalDate.now());
		box.setAmapSpace(amapSpace);
		box.setAmapProducerUser(aPU);
		if (oldBox.getImageData() != null) {
			box.setImageData(oldBox.getImageData());
		} 
		if (image != null && !image.isEmpty()) {
			try {
				box.setImageData(image.getBytes());
			} catch (IOException e) {
				redirectAttributes.addFlashAttribute("message", "Erreur lors de l'importation de l'image.");
				e.printStackTrace();
				return "redirect:/{slug}/myproducts";
				}
			} 
		
    	boxService.save(box);
        return "redirect:/{slug}/myproducts";
    }
	
	@GetMapping("/image/{id}")
	public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
	    Box box = boxService.findById(id);
	    if (box == null) {
	        System.out.println("Aucun panier trouvé avec l'ID : " + id);
	        return ResponseEntity.notFound().build();
	    }

	    byte[] imageData = box.getImageData();
	    if (imageData != null) {
	        System.out.println("Image trouvée pour l'ID : " + id);
	        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imageData);
	    }

	    System.out.println("Pas d'image pour l'ID : " + id);
	    return ResponseEntity.notFound().build();
	}
}