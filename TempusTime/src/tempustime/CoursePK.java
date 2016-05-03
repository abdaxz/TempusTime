package tempustime;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the Courses database table.
 * 
 */
@Embeddable
public class CoursePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="CourseName")
	private String courseName;

	@Column(name="Year")
	private int year;

	@Column(name="Term")
	private String term;

	public CoursePK() {
	}
	public String getCourseName() {
		return this.courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getYear() {
		return this.year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getTerm() {
		return this.term;
	}
	public void setTerm(String term) {
		this.term = term;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CoursePK)) {
			return false;
		}
		CoursePK castOther = (CoursePK)other;
		return 
			this.courseName.equals(castOther.courseName)
			&& (this.year == castOther.year)
			&& this.term.equals(castOther.term);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.courseName.hashCode();
		hash = hash * prime + this.year;
		hash = hash * prime + this.term.hashCode();
		
		return hash;
	}
}