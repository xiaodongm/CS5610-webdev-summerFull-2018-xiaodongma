/**
 * This file contains Assignment data model with extends class Widget.
 */
package webdev.models;

import javax.persistence.Entity;


@Entity
public class Assignment extends Widget {
	private String title;
	private String points;
	private String description;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPoints() {
		return points;
	}
	public void setPoints(String points) {
		this.points = points;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
