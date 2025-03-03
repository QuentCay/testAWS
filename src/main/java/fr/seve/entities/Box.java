package fr.seve.entities;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

@Entity
@Table(name = "boxes")
public class Box {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String name;

	@Lob
	@Column
	private String description;

	@Column
	private double price;

	@Column
	private int stock;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column
	private LocalDate availableDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column
	private LocalDate purchaseDeadlineDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column
	private LocalDate creationDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(nullable = true)
	private LocalDate lastModifiedDate;

	@Enumerated(EnumType.STRING)
	@Column
	private Category category;

	@Enumerated(EnumType.STRING)
	@Column
	private Frequency frequency;

	@Lob
	@Column(name = "image")
	private byte[] imageData;
	
	@ManyToOne
	@JoinColumn(name = "amapSpace_id")
	private AmapSpace amapSpace;

	
   @ManyToOne
   @JoinColumn(name = "producer_id", nullable = true)
   private AmapProducerUser amapProducerUser;

	
	public Box() {
	}
	
	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public LocalDate getAvailableDate() {
		return availableDate;
	}

	public void setAvailableDate(LocalDate availableDate) {
		this.availableDate = availableDate;
	}

	public LocalDate getPurchaseDeadlineDate() {
		return purchaseDeadlineDate;
	}

	public void setPurchaseDeadlineDate(LocalDate purchaseDeadlineDate) {
		this.purchaseDeadlineDate = purchaseDeadlineDate;
	}
	
	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public LocalDate getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(LocalDate lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public Frequency getFrequency() {
		return frequency;
	}

	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}
//    public String getImageUrl() {
//        return imageUrl;
//    }
//
//    public void setImageUrl(String imageUrl) {
//        this.imageUrl = imageUrl;
//    }

//    public Producer getProducer() {
//        return producer;
//    }
//
//    public void setProducer(Producer producer) {
//        this.producer = producer;
//    }
	
	
	
	// Formattage des dates
	
	public AmapProducerUser getAmapProducerUser() {
		return amapProducerUser;
	}

	public void setAmapProducerUser(AmapProducerUser amapProducerUser) {
		this.amapProducerUser = amapProducerUser;
	}

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public String getFormattedAvailableDate() {
	    return availableDate != null ? availableDate.format(FORMATTER) : "";
	}

	public String getFormattedPurchaseDeadlineDate() {
	    return purchaseDeadlineDate != null ? purchaseDeadlineDate.format(FORMATTER) : "";
	}
	
	public String getFormattedCreationDate() {
	    return creationDate != null ? creationDate.format(FORMATTER) : "";
	}

	public String getFormattedLastModifiedDate() {
	    return lastModifiedDate != null ? lastModifiedDate.format(FORMATTER) : "";
	}
	
	

	public AmapSpace getAmapSpace() {
		return amapSpace;
	}

	public void setAmapSpace(AmapSpace amapSpace) {
		this.amapSpace = amapSpace;
	}



	// Enum pour la fréquence
	public enum Frequency {
		WEEKLY("Hebdomadaire"), BIMONTHLY("Bimensuelle"), MONTHLY("Mensuelle");

		private final String displayName;

		Frequency(String displayName) {
			this.displayName = displayName;
		}

		public String getDisplayName() {
			return displayName;
		}
	}

	// Enum pour la catégorie
	public enum Category {
		FRUITS("Fruits"), VEGETABLES("Légumes"), MIXED("Mixte");

		private final String displayName;

		Category(String displayName) {
			this.displayName = displayName;
		}

		public String getDisplayName() {
			return displayName;
		}
	}

}
