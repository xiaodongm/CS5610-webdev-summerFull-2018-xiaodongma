package webdev.services;

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

import webdev.models.BaseExamQuestion;
import webdev.models.Exam;
import webdev.repositories.BaseExamQuestionRepository;
import webdev.repositories.ExamRepository;

@RestController
@CrossOrigin(origins = "*")
public class BaseExamQuestionService {
	@Autowired
	ExamRepository examRepository;
	
	@Autowired
	BaseExamQuestionRepository questionRepository;
	
	@GetMapping("/api/baseExamQuestion")
	public List<BaseExamQuestion> findAllBaseExamQuestions() {
		return (List<BaseExamQuestion>) questionRepository.findAll();
	}
	
	@GetMapping("/api/baseExamQuestion/{bqid}")
	public BaseExamQuestion findBaseExamQuestionsById(@PathVariable("bqid") int id) {
		Optional<BaseExamQuestion> data = questionRepository.findById(id);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@GetMapping("/api/exam/{examId}/baseExamQuestion")
	public List<BaseExamQuestion> findAllBaseExamQuestionsForExam(@PathVariable("examId") int examId) {
		Optional<Exam> data = examRepository.findById(examId);
		if(data.isPresent()) {
			Exam exam = data.get();
			return exam.getBaseExamQuestions();
		}
		return null;
	}
	
	
	@PostMapping("/api/exam/{examId}/baseExamQuestion")
	public BaseExamQuestion createBaseExamQuestion(
			@PathVariable("examId") int examId,
			@RequestBody BaseExamQuestion newQuestion) {
		Optional<Exam> data = examRepository.findById(examId);
		
		if(data.isPresent()) {
			Exam exam = data.get();
			newQuestion.setExam(exam);
			return questionRepository.save(newQuestion);
		}
		return null;		
	}
	
	@DeleteMapping("/api/baseExamQuestion/{bqid}")
	public void deleteBaseExamQuestion(@PathVariable("bqid") int id) {
		questionRepository.deleteById(id);
	}
	
	@PutMapping("/api/baseExamQuestion/{bqid}")
	public void updateBaseExamQuestion(@PathVariable("bqid") int id, @RequestBody BaseExamQuestion question) {
		Optional<BaseExamQuestion> data = questionRepository.findById(id);
		if(data.isPresent()) {
			BaseExamQuestion newQuestion = data.get();
			newQuestion.setPoints(question.getPoints());
			newQuestion.setTitle(question.getTitle());
			newQuestion.setDescription(question.getDescription());
			newQuestion.setInstructions(question.getInstructions());
			questionRepository.save(newQuestion);
		}
	}
	
	
	
}
