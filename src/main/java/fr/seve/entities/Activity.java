package fr.seve.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "activities")
public class Activity {

	@Id
	@GeneratedValue
	private Long id;

	@Column
	private String name;

	@Column
	private String description;

	@Column
	private double price;

//	@Column
//	private int maxParticipant;

	@Column
	private int availableSpace;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column
	private LocalDate date;

	@Column
	private LocalTime startTime;

	@Column
	private LocalTime endTime;

	@Column
	private String place;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column
	private LocalDate purchaseDeadlineDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column
	private LocalDate creationDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(nullable = true)
	private LocalDate lastModifiedDate;

	@Lob
	@Column(name = "image")
	private byte[] imageData;

	@ManyToOne
	@JoinColumn(name = "amapSpace_id")
	private AmapSpace amapSpace;

	@ManyToOne
	@JoinColumn(name = "producer_id", nullable = true)
	private AmapProducerUser amapProducerUser;

	public Activity() {

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
//
//	public int getMaxParticipant() {
//		return maxParticipant;
//	}
//
//	public void setMaxParticipant(int maxParticipant) {
//		this.maxParticipant = maxParticipant;
//	}

	public int getAvailableSpace() {
		return availableSpace;
	}

	public void setAvailableSpace(int availableSpace) {
		this.availableSpace = availableSpace;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
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

	public AmapProducerUser getAmapProducerUser() {
		return amapProducerUser;
	}

	public void setAmapProducerUser(AmapProducerUser amapProducerUser) {
		this.amapProducerUser = amapProducerUser;
	}

	public LocalDate getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(LocalDate lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

//	public String getImageUrl() {
//		return imageUrl;
//	}
//
//	public void setImageUrl(String imageUrl) {
//		this.imageUrl = imageUrl;
//	}
//
//	public Producer getProducer() {
//		return producer;
//	}
//
//	public void setProducer(Producer producer) {
//		this.producer = producer;
//	}

	// Formattage des dates

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public String getFormattedDate() {
		return date != null ? date.format(FORMATTER) : "";
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

	// Formattage des horaires

	private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

	public String getFormattedStartTime() {
		return startTime != null ? startTime.format(TIME_FORMATTER) : "";
	}

	public String getFormattedEndTime() {
		return endTime != null ? endTime.format(TIME_FORMATTER) : "";
	}

	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

	public AmapSpace getAmapSpace() {
		return amapSpace;
	}

	public void setAmapSpace(AmapSpace amapSpace) {
		this.amapSpace = amapSpace;
	}

}
