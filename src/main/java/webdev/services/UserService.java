/**
 * This file contains UserService use repositories to implement their respective behavior,
 * The Web service endpoints expose the URL patterns and actions needed to support the user
 * admin client application
 */
package webdev.services;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import webdev.models.User;
import webdev.repositories.UserRepository;

@RestController
public class UserService {
	@Autowired
	UserRepository repository;

	@DeleteMapping("/api/user/{userId}")
	public void deleteUser(@PathVariable("userId") int id) {
		repository.deleteById(id);
	}

	@PostMapping("/api/user")
	public User createUser(@RequestBody User user) {
		return repository.save(user);
	}

	@PutMapping("/api/user/{userId}")
	public User updateUser(@PathVariable("userId") int userId, @RequestBody User newUser) {
		Optional<User> data = repository.findById(userId);
		if (data.isPresent()) {
			User user = data.get();
			user.setFirstName(newUser.getFirstName());
			user.setLastName(newUser.getLastName());
			user.setUsername(newUser.getUsername());
			user.setPassword(newUser.getPassword());
			user.setRole(newUser.getRole());
			repository.save(user);
			return user;
		}
		return null;
	}

	@GetMapping("/api/user/{userId}")
	public User findUserById(@PathVariable("userId") int userId) {
		Optional<User> data = repository.findById(userId);
		if (data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@GetMapping("/api/user")	
	public Iterable<User> findAllUsers(@RequestParam(name="username", required=false) String username,
										@RequestParam(name="password", required=false) String password) {
		if(username != null && password != null) {
			return repository.findUserByCredentials(username, password);
		}
		else if(username != null) {
			return repository.findUserByUsername(username);
		}
		return repository.findAll();
	}
	
	@GetMapping("/api/user/findByUsername")
	public List<User> findUserByUsername(@RequestParam(name="username", required=true) String username){
		return (List<User>) repository.findUserByUsername(username);
	}
	

	@PostMapping("/api/register")
	public User register(@RequestBody User user, HttpSession session) throws Exception { 
		List<User> users = (List<User>) repository.findUserByUsername(user.getUsername());
		if(users.size() == 0) {
			createUser(user);
			session.setAttribute("user", user);
			return (User) session.getAttribute("user");
		}else {
			throw new Exception("Can not register");
		}
	}
	
	@GetMapping("/api/user/findByCredential")
	Iterable<User> findUserByCredentials(@RequestParam(name="username", required=true) String username,
										@RequestParam(name="password", required=true) String password){
		return repository.findUserByCredentials(username, password);
	}
	
	@PostMapping("/api/login")
	public User login(@RequestBody User user, HttpSession session) throws Exception {
		List<User> users = (List<User>) repository.findUserByCredentials(user.getUsername(), user.getPassword());
		if(users.size() != 0) {
			session.setAttribute("user", users.get(0));
			return (User) session.getAttribute("user");
		}else {
			throw new Exception("User not exsit");
		}
	}
	
	@PutMapping("/api/profile")
	public User updateProfile(@RequestBody User user, HttpSession session) { 
		User currentUser = (User) session.getAttribute("user");
		if(currentUser != null) {
			currentUser.setFirstName(user.getFirstName());
			currentUser.setLastName(user.getLastName());
			currentUser.setPhone(user.getPhone());
			currentUser.setEmail(user.getEmail());
			currentUser.setRole(user.getRole());
			currentUser.setDateOfBirth(user.getDateOfBirth());
			repository.save(currentUser);
			return currentUser;
		}
		return null;
	}
	
	@GetMapping("/api/profile")
	public User profile(HttpSession session) {
		User currentUser = (User)session.getAttribute("user");
		return currentUser;
	}


//	@PostMapping("/api/logout")
//	public void logout(HttpSession session) {
//		session.invalidate();
//	}

}
