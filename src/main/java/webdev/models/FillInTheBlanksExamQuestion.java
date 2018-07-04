/**
 * This file contains FillInTheBlanksExamQuestion data model
 */
package webdev.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;

@Entity
public class FillInTheBlanksExamQuestion extends BaseExamQuestion {
	@Column
	@ElementCollection(targetClass=String.class)
	private List<String> variables;

	public List<String> getVariables() {
		return variables;
	}

	public void setVariables(List<String> variables) {
		this.variables = variables;
	}
	
	
}
