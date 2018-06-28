/**
 * This file contains Exam data Model
 */
package webdev.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Exam extends Widget {
	private String Description;
	private int points;
	@OneToMany(mappedBy="exam", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<BaseExamQuestion> baseExamQuestions;
	
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public List<BaseExamQuestion> getBaseExamQuestions() {
		return baseExamQuestions;
	}
	public void setBaseExamQuestions(List<BaseExamQuestion> baseExamQuestions) {
		this.baseExamQuestions = baseExamQuestions;
	}
	
	
	
}
