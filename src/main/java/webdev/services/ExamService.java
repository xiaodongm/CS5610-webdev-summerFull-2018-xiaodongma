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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import webdev.models.Exam;
import webdev.models.Topic;
import webdev.models.Widget;
import webdev.repositories.ExamRepository;
import webdev.repositories.TopicRepository;

@RestController
@CrossOrigin(origins = "*")
public class ExamService {
	@Autowired
	ExamRepository examRepository;
	
	@Autowired
	TopicRepository topicRepository;
	
	@GetMapping("/api/exam")
	public List<Exam> findAllExams() {
		return (List<Exam>) examRepository.findAll();
	}
	
	@GetMapping("/api/exam/{eid}")
	public Exam findExamById(@PathVariable("eid") int id) {
		Optional<Exam> data = examRepository.findById(id);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@GetMapping("/api/topic/{topicId}/exam")
	public List<Widget> findAllExamsForTopic(@PathVariable("topicId") int topicId) {
		Optional<Topic> data = topicRepository.findById(topicId);
		if(data.isPresent()) {
			Topic topic = data.get();
			List<Widget> widgets = topic.getWidgets();
			List<Widget> exams = new ArrayList<>();
			for(Widget widget: widgets) {
				if(widget instanceof Exam) {
					exams.add(widget);
				}
			}
			return exams;
		}
		return null;
	}
	
	@PostMapping("/api/topic/{topicId}/exam")
	public Exam createExam(
			@PathVariable("topicId") int topicId,
			@RequestBody Exam newExam) {
		Optional<Topic> data = topicRepository.findById(topicId);
		
		if(data.isPresent()) {
			Topic topic = data.get();
			newExam.setTopic(topic);
			return examRepository.save(newExam);
		}
		return null;		
	}
	
	@DeleteMapping("/api/exam/{eid}")
	public void deleteExam(@PathVariable("eid") int examId) {
		Optional<Exam> data = examRepository.findById(examId);
		if(data.isPresent()) {
			data.get().getTopic().getLesson().getModule().getCourse().setModified(new Date());
		}
		examRepository.deleteById(examId);
	}
	
	@PutMapping("/api/exam/{eid}")
	public Exam updateExam(@PathVariable("eid") int examId, @RequestBody Exam exam) {
		Optional<Exam> data = examRepository.findById(examId);
		Exam newExam = new Exam();
		if(data.isPresent()) {
			newExam = data.get();
			newExam.setTitle(exam.getTitle());
			newExam.setDescription(exam.getDescription());
			newExam.setPoints(exam.getPoints());
			newExam.setBaseExamQuestions(exam.getBaseExamQuestions());
			examRepository.save(newExam);
		}
		return newExam;
	}
	
}
