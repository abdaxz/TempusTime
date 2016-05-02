package tempustime;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class CourseManager implements Serializable {

	private List<String> courseList = new ArrayList<String>(Arrays.asList(
			"1IK213",
			"1IK013",
			"1DV508"
			));
	
	private String selectedCourse;
	
	public List<String> getCourseList() {
		return courseList;
	}

	public String getSelectedCourse() {
		return selectedCourse;
	}

	public void setSelectedCourse(String selectedCourse) {
		this.selectedCourse = selectedCourse;
	}
		
}
