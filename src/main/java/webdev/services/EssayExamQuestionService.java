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
import webdev.models.EssayExamQuestion;
import webdev.models.Exam;
import webdev.repositories.EssayExamQuestionRepository;
import webdev.repositories.ExamRepository;

@RestController
@CrossOrigin(origins = "*")
public class EssayExamQuestionService {

	@Autowired
	ExamRepository examRepository;
	
	@Autowired
	EssayExamQuestionRepository repository;
	
	@GetMapping("/api/essay")
	public List<EssayExamQuestion> findAllEssayQuestions() {
		return (List<EssayExamQuestion>) repository.findAll();
	}
	
	@GetMapping("/api/essay/{eqid}")
	public EssayExamQuestion findEssayExamQuestionsById(@PathVariable("eqid") int id) {
		Optional<EssayExamQuestion> data = repository.findById(id);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@GetMapping("/api/exam/{examId}/essay")
	public List<EssayExamQuestion> findAllEssayExamQuestionsForExam(@PathVariable("examId") int examId) {
		Optional<Exam> data = examRepository.findById(examId);
		if(data.isPresent()) {
			Exam exam = data.get();
			List<BaseExamQuestion> questions = exam.getBaseExamQuestions();
			List<EssayExamQuestion> essayQuestions = new ArrayList<>();
			for(BaseExamQuestion question: questions) {
				if(question instanceof EssayExamQuestion) {
					essayQuestions.add((EssayExamQuestion)question);
				}
			}
			return essayQuestions;
		}
		return null;
	}
	
	@PostMapping("/api/exam/{examId}/essay")
	public EssayExamQuestion createEssayExamQuestion(
			@PathVariable("examId") int examId,
			@RequestBody EssayExamQuestion newQuestion) {
		Optional<Exam> data = examRepository.findById(examId);
		
		if(data.isPresent()) {
			Exam exam = data.get();
			newQuestion.setExam(exam);
			return repository.save(newQuestion);
		}
		return null;		
	}
	
	@DeleteMapping("/api/essay/{eqid}")
	public void deleteEssayExamQuestion(@PathVariable("emqid") int id) {
		repository.deleteById(id);
	}
	
	@PutMapping("/api/essay/{eqid}")
	public void updateEssayExamQuestion(@PathVariable("eqid") int id, @RequestBody EssayExamQuestion question) {
		Optional<EssayExamQuestion> data = repository.findById(id);
		if(data.isPresent()) {
			EssayExamQuestion newQuestion = data.get();
			newQuestion.setPoints(question.getPoints());
			newQuestion.setTitle(question.getTitle());
			newQuestion.setDescription(question.getDescription());
			newQuestion.setAnswer(question.getAnswer());
			repository.save(newQuestion);
		}
	}
	
}
