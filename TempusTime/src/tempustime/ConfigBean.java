package tempustime;

import java.io.Serializable;
import java.util.Calendar;
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
	private int newCourseYear;
	private String newCourseName;
	private String newCourseTerm;
	private int newCourseActive;
	private boolean newCourseActiveBool;

	@EJB
	private CourseBean theCourses;
	
	//private FacesContext context = FacesContext.getCurrentInstance();
	
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
	
	public String getNewCourseName() {
		return newCourseName;
	}

	public void setNewCourseName(String newCourseName) {
		this.newCourseName = newCourseName;
	}

	public String getNewCourseTerm() {
		return newCourseTerm;
	}

	public void setNewCourseTerm(String newCourseTerm) {
		this.newCourseTerm = newCourseTerm;
	}

	public int getNewCourseActive() {
		newCourseActive=0;
		return newCourseActive;
	}

	public void setNewCourseActive(int newCourseActive) {
		this.newCourseActive = newCourseActive;
	}

	public int getNewCourseYear() {
		newCourseYear = Calendar.getInstance().get(Calendar.YEAR);
		return newCourseYear;
	}

	public void setNewCourseYear(int newCourseYear) {
		this.newCourseYear = newCourseYear;
	}
	
	public void addNewCourse() {
		
		Course tmp = new Course();
		CoursePK tmpPK = new CoursePK();
		tmpPK.setCourseName(newCourseName);
		tmpPK.setTerm(newCourseTerm);
		tmpPK.setYear(newCourseYear);
		tmp.setId(tmpPK);
		tmp.setActive(newCourseActive);
		
		try {
			theCourses.addNewCourse(tmp);
			FacesMessage message = new FacesMessage("Kurs har lagts till", "Id: " + newCourseName);
	        FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (Exception ex) {
			FacesMessage msg = new FacesMessage("Ett fel uppstod", "Kursen kunde inte läggas till");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	/* public void onCourseAdded(SelectEvent event) {
		Course theCourse = (Course) event.getObject();
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Kurs har lagts till", "Id: " + theCourse.getId().getCourseName());
        
        FacesContext.getCurrentInstance().addMessage(null, message);
	}*/

	public boolean isNewCourseActiveBool() {
		if(newCourseActive == 1)
			newCourseActiveBool = true;
		else
			newCourseActiveBool = false;
				
		return newCourseActiveBool;
	}

	public void setNewCourseActiveBool(boolean newCourseActiveBool) {
		if(newCourseActiveBool == true)
			newCourseActive = 1;
		else
			newCourseActive = 0;
		
		this.newCourseActiveBool = newCourseActiveBool;
	}
}
