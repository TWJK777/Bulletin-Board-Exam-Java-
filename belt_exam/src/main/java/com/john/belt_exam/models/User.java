package com.john.belt_exam.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(name="users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Pattern(regexp="^[A-Za-z]*$", message="letters only, at least 3 characters, not blank")
    @NotEmpty(message="Username is required!")
    @Size(min=3, max=128, message="letters only, at least 3 characters, not blank")
    private String userName;
    
    @NotEmpty(message="Email is required!")
    @Email(message="Please enter a valid email!")
    private String email;
    
    @NotEmpty(message="Password is required!")
    @Size(min=8, max=128, message="Password must be at least 8 characters, not blank")
    private String password;
    
    @Transient
    @NotEmpty(message="Confirm Password is required!")
    @Size(min=8, max=128, message="matches password")
    private String confirm;
    
    
 // 1:M
    @OneToMany(mappedBy = "creator", fetch = FetchType.LAZY)
    private List<Television> televisions;
    //GETTERS AND SETTERS
    
    
 // Must have an empty constructor
    public User() {}
    
    
    
///// Getters And Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	
	public List<Television> getTelevisions() {
		return televisions;
	}




	public void setTelevisions(List<Television> televisions) {
		this.televisions = televisions;
	}
}
