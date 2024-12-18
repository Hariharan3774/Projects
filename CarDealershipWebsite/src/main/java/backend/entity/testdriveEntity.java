package backend.entity;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="testdrive")
public class testdriveEntity {
	
	@Id
	private String email;
	
	private String name;
	private Long phone;
	private String model;
	private LocalDate testdate;
	private LocalTime testtime;
	private String question;
	private String carname;
	
	
	public String getCarname() {
		return carname;
	}
	public void setCarname(String carname) {
		this.carname = carname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public LocalDate getTestdate() {
		return testdate;
	}
	public void setTestdate(LocalDate testdate) {
		this.testdate = testdate;
	}
	public LocalTime getTesttime() {
        return testtime;
    }
    public void setTesttime(LocalTime testtime) {
        this.testtime = testtime;
    }
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
	
}
