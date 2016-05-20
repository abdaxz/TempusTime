package tempustime;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;

@Named
@ViewScoped
public class ConfigBean implements Serializable {
	
	private List<Course> courses = null;
	private Course courseSelection;
	
	@EJB
	private CourseBean theCourses;
	
	@PostConstruct
	public void init() {
		courses = theCourses.getListOfCourses();
	}
	
	public List<Course> getCourses() {
		return courses;
	}
	
	public Course getCourseSelection() {
		return courseSelection;
	}

	public void setCourseSelection(Course courseSelection) {
		this.courseSelection = courseSelection;
	}
	
	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Course Edited", ((Course) event.getObject()).getId().getCourseName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}
