package tempustime;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;


@Named
@ViewScoped
public class CourseManager implements Serializable {
	private static final long serialVersionUID = 240902154191546462L;
	
	@EJB
	private CourseBean theCourses;
	
	public CourseManager() {
	}

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
			"Tentarättning",
			"Möte",
			"Övrigt"
			));
	
	private String activity;
	
	private String duration;
	
	private String selectedCourse;
	
	private String currCourse;
	private String currTerm;
	private int currYear;
	private int currDuration;
	
	private List<String> courseList = new ArrayList<String>();
	
	@PostConstruct
	public void initCourseList() {
		List<Course> listOfCourses = theCourses.getListOfCourses();
		
		
		for(int i = 0; i < listOfCourses.size(); i++)
		{
			if(listOfCourses.get(i).getActive()!=0)
			{
				String theCourse = listOfCourses.get(i).getId().getCourseName() + " " + listOfCourses.get(i).getId().getTerm() + " " + listOfCourses.get(i).getId().getYear();
				courseList.add(theCourse);
			}
		}
	}
	
	public List<String> getCourseList() {		
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
	
	public void result() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
        
		if(selectedCourse!=null || duration!=null || activity!=null ) {
			String[] parts = selectedCourse.split(" ");
			currCourse = parts[0];
			currTerm = parts[1];
			currYear = Integer.parseInt(parts[2]);
			String[] durationParts = duration.split(" ");
			currDuration = Integer.parseInt(durationParts[0]);
			
			CourseActivity ca = new CourseActivity();
	    	
	    	ca.setName(currCourse);
	    	ca.setTerm(currTerm);
	    	ca.setYear(currYear);
	    	ca.setActivity(activity);
	    	ca.setDuration(currDuration);
	    	
			try {
				theCourses.addCourseActivity(ca);
				context.addMessage(null, new FacesMessage("Inlagt!",  duration + " minuter inlagt för " + activity + " på kursen " + parts[0] + " " + parts[1] + parts[2]) );
			} catch (Exception e) {
				context.addMessage(null, new FacesMessage("Ett fel har uppstått!",  "Det gick inte att lägga in i databasen.") );
			}			
		}
		else
			context.addMessage(null, new FacesMessage("Ett fel!",  "Inte allt var valt!") );
	}		
}
