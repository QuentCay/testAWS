package fr.seve.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import fr.seve.entities.Box.Category;

@Entity
@Table(name= "configurations")
public class Configuration {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@Lob
	@Column
	private String presentationText;
	
	@Column
	private String primaryColor;
	
	@Column
	private String secondaryColor;
	
	@Column
	private String tertiaryColor;
	
    @Enumerated(EnumType.STRING)
    @Column
    private Police police = Police.ARIAL;
	
	@Column
	private Boolean isRoundedBorders;
	
	@Lob
	@Column(name = "logo")
	private byte[] logoData;
	
	@Lob
	@Column(name = "presentationImage")
	private byte[] presentationImageData;
	
	
	@OneToOne(mappedBy = "configuration", cascade = CascadeType.ALL, orphanRemoval = true)
	private AmapSpace amapSpace;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getPresentationText() {
		return presentationText;
	}

	public void setPresentationText(String presentationText) {
		this.presentationText = presentationText;
	}

	public AmapSpace getAmapSpace() {
	    return amapSpace;
	}

	public void setAmapSpace(AmapSpace amapSpace) {
	    this.amapSpace = amapSpace;
	}

	public String getPrimaryColor() {
		return primaryColor;
	}

	public void setPrimaryColor(String primaryColor) {
		this.primaryColor = primaryColor;
	}

	public String getSecondaryColor() {
		return secondaryColor;
	}

	public void setSecondaryColor(String secondaryColor) {
		this.secondaryColor = secondaryColor;
	}

	public String getTertiaryColor() {
		return tertiaryColor;
	}

	public void setTertiaryColor(String tertiaryColor) {
		this.tertiaryColor = tertiaryColor;
	}

	public Police getPolice() {
		return police;
//		return police != null ? police : Police.DEFAULT;
	}

	public void setPolice(Police police) {
		this.police = police;
	}

	public Boolean getIsRoundedBorders() {
	    return isRoundedBorders;
	}

	public void setIsRoundedBorders(Boolean isRoundedBorders) {
	    this.isRoundedBorders = isRoundedBorders;
	}
	
	public byte[] getLogoData() {
		return logoData;
	}

	public void setLogoData(byte[] logoData) {
		this.logoData = logoData;
	}

	public enum Police {
	    ARIAL("Arial"),
	    BASKERVILLE("Baskerville"),
	    CALIBRI("Calibri"),
	    CAMBRIA("Cambria"),
	    COURIER_NEW("Courier New"),
	    CONSOLAS("Consolas"),
	    FUTURA("Futura"),
	    GARAMOND("Garamond"),
	    GEORGIA("Georgia"),
	    HELVETICA("Helvetica"),
	    LATO("Lato"),
	    MONTSERRAT("Montserrat"),
	    OPEN_SANS("Open Sans"),
	    PALATINO("Palatino"),
	    POPPINS("Poppins"),
	    ROBOTO("Roboto"),
	    SOURCE_SANS_PRO("Source Sans Pro"),
	    TAHOMA("Tahoma"),
	    TIMES_NEW_ROMAN("Times New Roman"),
	    VERDANA("Verdana");
//	    DEFAULT("Arial");

	    private final String displayName;

	    Police(String displayName) {
	        this.displayName = displayName;
	    }

	    public String getDisplayName() {
	        return displayName;
	    }
	}

	public byte[] getPresentationImageData() {
		return presentationImageData;
	}

	public void setPresentationImageData(byte[] presentationImageData) {
		this.presentationImageData = presentationImageData;
	}

	
}
