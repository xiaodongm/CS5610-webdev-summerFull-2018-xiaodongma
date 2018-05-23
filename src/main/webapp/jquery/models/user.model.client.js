/**
 * This file implements a User JavaScript class with the following properties:
 * username, password, email, firstName, lastName, phone, role, and dateOfBirth.
 * Use this class to create instances of users whenever sending or receiving
 * data from the server, or rendering users in the UI.
 */
function User(username, password, firstName, lastName, role, email, phone, dateOfBirth) {
	this.username = username;
	this.password = password;
	this.firstName = firstName;
	this.lastName = lastName;
	this.role = role;
	this.email = email;
	this.phone = phone;
	this.dateOfBirth = dateOfBirth;

	this.setUsername = setUsername;
	this.getUsername = getUsername;
	this.setPassword = setPassword;
	this.getPassword = getPassword;
	this.setFirstName = setFirstName;
	this.getFirstName = getFirstName;
	this.setLastName = setLastName;
	this.getLastName = getLastName;
	this.setRole = setRole;
	this.getRole = getRole;
	this.setEmail = setEmail;
	this.getEmail = getEmail;
	this.setPhone = setPhone;
	this.getPhone = getPhone;
	this.setDateOfBirth = setDateOfBirth;
	this.getDateOfBirth = getDateOfBirth;

	function setUsername(username) {
		this.username = username;
	}
	function getUsername() {
		return this.username;
	}
	
	function setPassword(password) {
		this.password = password;
	}
	function getPassword() {
		return this.password;
	}

	function setFirstName(firstname) {
		this.firstName = firstname;
	}
	function getFirstName() {
		return this.firstName;
	}
	
	function setLastName(lastname) {
		this.lastName = lastname;
	}
	function getLastName() {
		return this.lastName;
	}
	
	function setRole(role) {
		this.role = role;
	}
	function getRole() {
		return this.role;
	}
	
	function setEmail(email) {
		this.email = email;
	}
	function getEmail() {
		return this.email;
	}
	
	function setPhone(phone) {
		this.phone = phone;
	}
	function getPhone() {
		return this.phone;
	}
	
	function setDateOfBirth(dateOfBirth) {
		this.dateOfBirth = dateOfbirth;
	}
	function getDateOfBirth() {
		return this.dateOfBirth;
	}

}
