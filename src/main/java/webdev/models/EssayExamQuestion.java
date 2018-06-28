/**
 * This file contains EssayExamQuestion data model
 */
package webdev.models;

import javax.persistence.Entity;

@Entity
public class EssayExamQuestion extends BaseExamQuestion {
	private String answer;

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
}
