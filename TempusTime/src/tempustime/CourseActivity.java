package tempustime;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CourseActivity database table.
 * 
 */
@Entity
@NamedQuery(name="CourseActivity.findAll", query="SELECT c FROM CourseActivity c")
public class CourseActivity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;

	@Column(name="Activity")
	private String activity;

	@Column(name="Duration")
	private int duration;

	@Column(name="Name")
	private String name;

	@Column(name="Term")
	private String term;

	@Column(name="Year")
	private int year;

	public CourseActivity() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getActivity() {
		return this.activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public int getDuration() {
		return this.duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTerm() {
		return this.term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}