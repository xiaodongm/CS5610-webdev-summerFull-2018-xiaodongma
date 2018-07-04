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
import webdev.models.FillInTheBlanksExamQuestion;
import webdev.repositories.ExamRepository;
import webdev.repositories.FillInTheBlanksExamQuestionRepository;

@RestController
@CrossOrigin(origins = "*")
public class FillInTheBlanksExamQuestionService {

	@Autowired
	ExamRepository examRepository;
	
	@Autowired
	FillInTheBlanksExamQuestionRepository repository;
	
	@GetMapping("/api/blanks")
	public List<FillInTheBlanksExamQuestion> findAllFillInTheBlanksQuestions() {
		return (List<FillInTheBlanksExamQuestion>) repository.findAll();
	}
	
	@GetMapping("/api/blanks/{fqid}")
	public FillInTheBlanksExamQuestion findFillInTheBlanksExamQuestionsById(@PathVariable("fqid") int id) {
		Optional<FillInTheBlanksExamQuestion> data = repository.findById(id);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@GetMapping("/api/exam/{examId}/blanks")
	public List<FillInTheBlanksExamQuestion> findAllFillInTheBlanksExamQuestionsForExam(@PathVariable("examId") int examId) {
		Optional<Exam> data = examRepository.findById(examId);
		if(data.isPresent()) {
			Exam exam = data.get();
			List<BaseExamQuestion> questions = exam.getBaseExamQuestions();
			List<FillInTheBlanksExamQuestion> fillInTheBlanksQuestions = new ArrayList<>();
			for(BaseExamQuestion question: questions) {
				if(question instanceof FillInTheBlanksExamQuestion) {
					fillInTheBlanksQuestions.add((FillInTheBlanksExamQuestion)question);
				}
			}
			return fillInTheBlanksQuestions;
		}
		return null;
	}
	
	@PostMapping("/api/exam/{examId}/blanks")
	public FillInTheBlanksExamQuestion createFillInTheBlanksExamQuestion(
			@PathVariable("examId") int examId,
			@RequestBody FillInTheBlanksExamQuestion newQuestion) {
		Optional<Exam> data = examRepository.findById(examId);
		if(data.isPresent()) {
			Exam exam = data.get();
			newQuestion.setExam(exam);
			return repository.save(newQuestion);
		}
		return null;		
	}
	
	@DeleteMapping("/api/blanks/{fqid}")
	public void deleteFillInTheBlanksExamQuestion(@PathVariable("fqid") int id) {
		repository.deleteById(id);
	}
	
	@PutMapping("/api/blanks/{fqid}")
	public void updateFillInTheBlanksExamQuestion(@PathVariable("fqid") int id, @RequestBody FillInTheBlanksExamQuestion question) {
		Optional<FillInTheBlanksExamQuestion> data = repository.findById(id);
		if(data.isPresent()) {
			FillInTheBlanksExamQuestion newQuestion = data.get();
			newQuestion.setPoints(question.getPoints());
			newQuestion.setTitle(question.getTitle());
			newQuestion.setDescription(question.getDescription());
			newQuestion.setVariables(question.getVariables());
			repository.save(newQuestion);
		}
	}
	
}
