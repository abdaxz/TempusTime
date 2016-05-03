package tempustime;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Courses database table.
 * 
 */
@Entity
@Table(name="Courses")
@NamedQuery(name="Course.findAll", query="SELECT c FROM Course c")
public class Course implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CoursePK id;

	@Column(name="Active")
	private int active;

	public Course() {
	}

	public CoursePK getId() {
		return this.id;
	}

	public void setId(CoursePK id) {
		this.id = id;
	}

	public int getActive() {
		return this.active;
	}

	public void setActive(int active) {
		this.active = active;
	}

}