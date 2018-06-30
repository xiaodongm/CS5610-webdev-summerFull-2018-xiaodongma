package webdev.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import webdev.models.Assignment;
import webdev.models.Topic;
import webdev.models.Widget;
import webdev.repositories.AssignmentRepository;
import webdev.repositories.TopicRepository;

@RestController
@CrossOrigin(origins = "*")
public class AssignmentService {

	@Autowired
	AssignmentRepository assignmentRepository;
	
	@Autowired
	TopicRepository topicRepository;
	
	@GetMapping("/api/assignment")
	public List<Assignment> findAllAssignments() {
		return (List<Assignment>) assignmentRepository.findAll();
	}
	
	@GetMapping("/api/assignment/{aid}")
	public Assignment findAssignmentById(@PathVariable("aid") int id) {
		Optional<Assignment> data = assignmentRepository.findById(id);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@GetMapping("/api/topic/{topicId}/assignment")
	public List<Widget> findAllAssignmentsForTopic(@PathVariable("topicId") int topicId) {
		Optional<Topic> data = topicRepository.findById(topicId);
		if(data.isPresent()) {
			Topic topic = data.get();
			List<Widget> widgets = topic.getWidgets();
			List<Widget> assignments = new ArrayList<>();
			for(Widget widget: widgets) {
				if(widget instanceof Assignment) {
					assignments.add(widget);
				}
			}
			return assignments;
		}
		return new ArrayList<>();
	}
	
	@PostMapping("/api/topic/{topicId}/assignment")
	public Assignment createAssignment(
			@PathVariable("topicId") int topicId,
			@RequestBody Assignment newAssignment) {
		Optional<Topic> data = topicRepository.findById(topicId);
		
		if(data.isPresent()) {
			Topic topic = data.get();
			newAssignment.setTopic(topic);
			return assignmentRepository.save(newAssignment);
		}
		return null;		
	}
	
	@DeleteMapping("/api/assignment/{aid}")
	public void deleteAssignment(@PathVariable("aid") int assignmentId) {
		Optional<Assignment> data = assignmentRepository.findById(assignmentId);
		if(data.isPresent()) {
			data.get().getTopic().getLesson().getModule().getCourse().setModified(new Date());
		}
		assignmentRepository.deleteById(assignmentId);
	}
	
	
	
	
}
