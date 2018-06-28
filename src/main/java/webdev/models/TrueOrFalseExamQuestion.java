/**
 * This file contains TrueOrFalseExamQuestion data model
 */
package webdev.models;

import javax.persistence.Entity;

@Entity
public class TrueOrFalseExamQuestion extends BaseExamQuestion {
	private Boolean isTrue;

	public Boolean getIsTrue() {
		return isTrue;
	}

	public void setIsTrue(Boolean isTrue) {
		this.isTrue = isTrue;
	}
	
}
