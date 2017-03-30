package vaadin_archetype_application.Assignment5;

import java.io.Serializable;

/** 
 * Game class for Assignment 5 - HCI
 * 3/30/17
 * @author Emily Black
 * Game catalog interface.
 */
@SuppressWarnings("serial")
public class Game implements Serializable, Cloneable {

	private Long id;

	private String title = "";

	private String developer = "";

	private Genre status;

	private String year = "";

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Genre getStatus() {
		return status;
	}

	public void setStatus(Genre status) {
		this.status = status;
	}

	public String getDeveloper() {
		return developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isPersisted() {
		return id != null;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (this.id == null) {
			return false;
		}

		if (obj instanceof Game && obj.getClass().equals(getClass())) {
			return this.id.equals(((Game) obj).id);
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 43 * hash + (id == null ? 0 : id.hashCode());
		return hash;
	}

	@Override
	public Game clone() throws CloneNotSupportedException {
		return (Game) super.clone();
	}

	@Override
	public String toString() {
		return title + " " + developer;
	}
}