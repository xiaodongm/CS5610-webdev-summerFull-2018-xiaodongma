/**
 * This file contains UserService use repositories to implement their respective behavior,
 * The Web service endpoints expose the URL patterns and actions needed to support the user
 * admin client application
 */
package webdev.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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

//	@PostMapping("/api/login")
//	public List<User> login(@RequestBody User user) {
//		return (List<User>) repository.findUserByCredentials(user.getUsername(), user.getPassword());
//	}

	@GetMapping("/api/user")
	public List<User> findAllUsers() {
		return (List<User>) repository.findAll();
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
	public Iterable<User> findAllUsers(@RequestParam(name="username", required=false) String username) {
		if(username != null) {
			return repository.findUserByUsername(username);
		}
		return repository.findAll();
	}
	
	@GetMapping("/api/user")
	public Iterable<User> findUserByUsername(@RequestParam(name="username", required=false) String username){
		return repository.findUserByUsername(username);
	}

}
