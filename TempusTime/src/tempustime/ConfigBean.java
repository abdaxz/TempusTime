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
import org.primefaces.event.SelectEvent;

@Named
@ViewScoped
public class ConfigBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private List<Course> courses = null;
	private Course courseSelection;
	private Course oldCourse = new Course();
	
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
	
	public void onRowSelect(SelectEvent event) {
		if(courseSelection != null) {
			CoursePK tmp = new CoursePK();
			
			String tName = courseSelection.getId().getCourseName();
			String tTerm = courseSelection.getId().getTerm();
			int tYear = courseSelection.getId().getYear();
			
			tmp.setCourseName(tName);
			tmp.setTerm(tTerm);
			tmp.setYear(tYear);
			
			int tActive = courseSelection.getActive();
			
			oldCourse.setId(tmp);
			oldCourse.setActive(tActive);
		}
	}
	
	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Course Edited", ((Course) event.getObject()).getId().getCourseName() + " was " + oldCourse.getId().getCourseName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}
