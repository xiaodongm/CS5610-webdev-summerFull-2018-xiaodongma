/**
 * This file contains Exam data Model
 */
package webdev.models;

import javax.persistence.Entity;

@Entity
public class Exam extends Widget {
	private String Description;
	private int points;
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
	
}
