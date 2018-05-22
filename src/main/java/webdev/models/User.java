/**
 * This file contains User class implements the user data model 
 * including properties that describe all users
 */
package webdev.models;

import java.util.Date;
import javax.persistence.*;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	private String role;
	private Date dateOfBirth;
	
	/**
	 * get user id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * set user id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * get username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * set username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * get password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * set password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * get firsname
	 * @return
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * set firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * get lastname
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * set lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * get phone
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * set phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * get email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * set email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * get role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * set role
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * get DateOfBirth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	
	/**
	 * set dateOfBirth
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

}
