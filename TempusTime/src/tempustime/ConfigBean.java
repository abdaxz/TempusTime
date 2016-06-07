package tempustime;

import java.io.Serializable;
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
	
	private FacesContext context = FacesContext.getCurrentInstance();
	
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
	
	public void deleteCourse() {
		if(courseSelection==null) {
			FacesMessage msg = new FacesMessage("Ett fel uppstod", "Kursen kunde inte tas bort");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} 
		else 
		{
			try {
				theCourses.deleteCourse(courseSelection.getId());
			} catch (Exception ex) {
				FacesMessage msg = new FacesMessage("Ett fel uppstod", "Kursen kunde inte tas bort");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			
			FacesMessage msg = new FacesMessage("Kursen borttagen", "Alla aktiviteter är också borttagna");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void onRowEdit(RowEditEvent event) {
		String active;
		
		if(((Course) event.getObject()).getActive()==0)
			active = "inaktiv";
		else
			active = "aktiv";
		
		FacesMessage msg = new FacesMessage("Kursen har ändrats", ((Course) event.getObject()).getId().getCourseName() + " är nu " + active);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        theCourses.updateCourseStatus(courseSelection.getId(), ((Course) event.getObject()).getActive());
	}
}
