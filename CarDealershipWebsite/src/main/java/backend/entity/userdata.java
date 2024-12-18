package backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="userdata")
public class userdata {
	@Id
	private String email;
	
	@Column(nullable=true, length=50)
	private String fname;
	
	@Column(nullable=true, length=50)
	private String lname;
	
	@Column(nullable=true, length=300)
	private String address;
	
	@Column(nullable=true, length=10)
	private Long phone;
	
	@Column(nullable=true, length=100)
	private String pass;


	public String getEmail() {
		return email;
	}





	public void setEmail(String email) {
		this.email = email;
	}





	public String getFname() {
		return fname;
	}





	public void setFname(String fname) {
		this.fname = fname;
	}





	public String getLname() {
		return lname;
	}





	public void setLname(String lname) {
		this.lname = lname;
	}





	public String getAddress() {
		return address;
	}





	public void setAddress(String address) {
		this.address = address;
	}





	public Long getPhone() {
		return phone;
	}





	public void setPhone(Long phone) {
		this.phone = phone;
	}





	public String getPass() {
		return pass;
	}





	public void setPass(String pass) {
		this.pass= pass;
	}


	
	
}