/**
 * This file contains course web services endpoint
 */
package webdev.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import webdev.models.Course;
import webdev.repositories.CourseRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseServices {
	@Autowired
	CourseRepository courseRepository;
	
	@GetMapping("/api/course")
	public Iterable<Course> findAllCourses() {
		return courseRepository.findAll(); 
	}
}
