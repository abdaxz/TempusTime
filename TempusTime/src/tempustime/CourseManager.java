package tempustime;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class CourseManager implements Serializable {
	private static final long serialVersionUID = 240902154191546462L;
	
	@EJB
	private CourseBean theCourses;

	/*private List<String> courseList = new ArrayList<String>(Arrays.asList(
			"1IK213",
			"1IK013",
			"1DV508"
			));*/
	
	private List<String> durationList = new ArrayList<String>(Arrays.asList(
			"5 min",
			"10 min",
			"15 min",
			"20 min",
			"30 min",
			"45 min",
			"60 min",
			"90 min",
			"120 min"
			));
	
	private List<String> activityList = new ArrayList<String>(Arrays.asList(
			"Kursansvar",
			"Planering",
			"Handledning",
			"Föreläsning",
			"Planera föreläsning",
			"Hålla labb",
			"Labbkonstruktion",
			"Labbrättning",
			"Tentakonstruktion",
			"Tentarättning"
			));
	
	private String activity;
	
	private String duration;
	
	private String selectedCourse;
	
	public List<String> getCourseList() {
		List<Course> listOfCourses = theCourses.getListOfCourses();
		List<String> courseList = new ArrayList<String>();
		
		for(int i = 0; i < listOfCourses.size(); i++)
		{
			if(listOfCourses.get(i).getActive()!=0)
			{
				String theCourse = listOfCourses.get(i).getId().getCourseName() + listOfCourses.get(i).getId().getTerm() + listOfCourses.get(i).getId().getYear();
				courseList.add(theCourse);
			}
		}
		
		return courseList;
	}

	public String getSelectedCourse() {
		return selectedCourse;
	}

	public void setSelectedCourse(String selectedCourse) {
		this.selectedCourse = selectedCourse;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	public List<String> getDurationList() {
		return durationList;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}
	
	public List<String> getActivityList() {
		return activityList;
	}
	
	public String result() {
		return selectedCourse + " " + duration + " " + activity;
	}
		
}
