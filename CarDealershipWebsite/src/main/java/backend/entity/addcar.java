package backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="addcar")
public class addcar {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "carid")
	private int carid;
	
	@Column(nullable=true, length=50)
	private String image;
	
	@Column(nullable=true, length=50)
	private String name;
	
	@Column(nullable=true, length=300)
	private Integer price;
	
	@Column(nullable=true, length=300)
	private String model;
	
	@Column(nullable=true, length=10)
	private String description;
	
	@Column(nullable=true, length=100)
	private String mm;
	
	@Column(nullable=true, length=100)
	private String hp;
	
	@Column(nullable=true, length=100)
	private String gear;
	
	@Column
	private int sold;
	
	public int getSold() {
		return sold;
	}

	public void setSold(int sold) {
		this.sold = sold;
	}

	public int getCarid() {
		return carid;
	}

	public void setCarid(int carid) {
		this.carid = carid;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMm() {
		return mm;
	}

	public void setMm(String mm) {
		this.mm = mm;
	}

	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	public String getGear() {
		return gear;
	}

	public void setGear(String gear) {
		this.gear = gear;
	}
	

}


