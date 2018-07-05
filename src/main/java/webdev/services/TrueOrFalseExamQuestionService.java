package webdev.services;

import java.util.ArrayList;
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
import webdev.models.TrueOrFalseExamQuestion;
import webdev.repositories.ExamRepository;
import webdev.repositories.TrueOrFalseExamQuestionRepository;

@RestController
@CrossOrigin(origins = "*")
public class TrueOrFalseExamQuestionService {
	@Autowired
	ExamRepository examRepository;
	
	@Autowired
	TrueOrFalseExamQuestionRepository repository;
	
	@GetMapping("/api/truefalse")
	public List<TrueOrFalseExamQuestion> findAllTrueOrFalseQuestions() {
		return (List<TrueOrFalseExamQuestion>) repository.findAll();
	}
	
	@GetMapping("/api/truefalse/{tqid}")
	public TrueOrFalseExamQuestion findTrueOrFalseExamQuestionsById(@PathVariable("tqid") int id) {
		Optional<TrueOrFalseExamQuestion> data = repository.findById(id);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	
	@GetMapping("/api/exam/{examId}/truefalse")
	public List<TrueOrFalseExamQuestion> findAllTrueOrFalseExamQuestionsForExam(@PathVariable("examId") int examId) {
		Optional<Exam> data = examRepository.findById(examId);
		if(data.isPresent()) {
			Exam exam = data.get();
			List<BaseExamQuestion> questions = exam.getBaseExamQuestions();
			List<TrueOrFalseExamQuestion> trueOrFalseQuestions = new ArrayList<>();
			for(BaseExamQuestion question: questions) {
				if(question instanceof TrueOrFalseExamQuestion) {
					trueOrFalseQuestions.add((TrueOrFalseExamQuestion)question);
				}
			}
			return trueOrFalseQuestions;
		}
		return null;
	}
	
	@PostMapping("/api/exam/{examId}/truefalse")
	public TrueOrFalseExamQuestion createTrueOrFalseExamQuestion(
			@PathVariable("examId") int examId,
			@RequestBody TrueOrFalseExamQuestion newQuestion) {
		Optional<Exam> data = examRepository.findById(examId);
		if(data.isPresent()) {
			Exam exam = data.get();
			newQuestion.setExam(exam);
			return repository.save(newQuestion);
		}
		return null;		
	}
	
	@DeleteMapping("/api/truefalse/{tqid}")
	public void deleteTrueOrFalseExamQuestion(@PathVariable("tqid") int id) {
		repository.deleteById(id);
	}
	
	@PutMapping("/api/truefalse/{tqid}")
	public void updateTrueOrFalseExamQuestion(@PathVariable("tqid") int id, @RequestBody TrueOrFalseExamQuestion question) {
		Optional<TrueOrFalseExamQuestion> data = repository.findById(id);
		if(data.isPresent()) {
			TrueOrFalseExamQuestion newQuestion = data.get();
			newQuestion.setPoints(question.getPoints());
			newQuestion.setTitle(question.getTitle());
			newQuestion.setDescription(question.getDescription());
			newQuestion.setIsTrue(question.getIsTrue());
			repository.save(newQuestion);
		}
	}
	
	
	
}
