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
import webdev.models.MultipleChoiceExamQuestion;
import webdev.repositories.ExamRepository;
import webdev.repositories.MultipleChoiceExamQuestionRepository;

@RestController
@CrossOrigin(origins = "*")
public class MultipleChoiceExamQuestionService {
	@Autowired
	ExamRepository examRepository;
	
	@Autowired
	MultipleChoiceExamQuestionRepository repository;
	
	@GetMapping("/api/choice")
	public List<MultipleChoiceExamQuestion> findAllMultipleChoiceQuestions() {
		return (List<MultipleChoiceExamQuestion>) repository.findAll();
	}
	
	@GetMapping("/api/choice/{mqid}")
	public MultipleChoiceExamQuestion findMultipleChoiceExamQuestionsById(@PathVariable("mqid") int id) {
		Optional<MultipleChoiceExamQuestion> data = repository.findById(id);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@GetMapping("/api/exam/{examId}/choice")
	public List<MultipleChoiceExamQuestion> findAllMultipleChoiceExamQuestionsForExam(@PathVariable("examId") int examId) {
		Optional<Exam> data = examRepository.findById(examId);
		if(data.isPresent()) {
			Exam exam = data.get();
			List<BaseExamQuestion> questions = exam.getBaseExamQuestions();
			List<MultipleChoiceExamQuestion> multipleChoiceQuestions = new ArrayList<>();
			for(BaseExamQuestion question: questions) {
				if(question instanceof MultipleChoiceExamQuestion) {
					multipleChoiceQuestions.add((MultipleChoiceExamQuestion)question);
				}
			}
			return multipleChoiceQuestions;
		}
		return null;
	}
	
	@PostMapping("/api/exam/{examId}/choice")
	public MultipleChoiceExamQuestion createMultipleChoiceExamQuestion(
			@PathVariable("examId") int examId,
			@RequestBody MultipleChoiceExamQuestion newQuestion) {
		Optional<Exam> data = examRepository.findById(examId);
		
		if(data.isPresent()) {
			Exam exam = data.get();
			newQuestion.setExam(exam);
			return repository.save(newQuestion);
		}
		return null;		
	}
	
	@DeleteMapping("/api/choice/{mqid}")
	public void deleteMultipleChoiceExamQuestion(@PathVariable("mqid") int id) {
		repository.deleteById(id);
	}
	
	
	@PutMapping("/api/choice/{mqid}")
	public void updateMultipleChoiceExamQuestion(@PathVariable("mqid") int id, @RequestBody MultipleChoiceExamQuestion question) {
		Optional<MultipleChoiceExamQuestion> data = repository.findById(id);
		if(data.isPresent()) {
			MultipleChoiceExamQuestion newQuestion = data.get();
			newQuestion.setPoints(question.getPoints());
			newQuestion.setTitle(question.getTitle());
			newQuestion.setDescription(question.getDescription());
			newQuestion.setInstructions(question.getInstructions());
			newQuestion.setCorrectOption(question.getCorrectOption());
			newQuestion.setOptions(question.getOptions());
			repository.save(newQuestion);
		}
	}
	
	
}
