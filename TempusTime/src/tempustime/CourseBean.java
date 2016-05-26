package tempustime;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Session Bean implementation class CourseBean
 */
@Stateless
public class CourseBean {

    @PersistenceContext(name="TempusTimer")
    EntityManager em;
	
    public CourseBean() {
    }
    
    public List<Course> getListOfCourses() {
    	// I frågan är det entiteten -- inte databasen -- som efterfrågas!
    	TypedQuery<Course> theQuery = em.createQuery("SELECT c FROM Course c", Course.class);
    	List<Course> result = theQuery.getResultList();
    	
    	return result;
    }
    
    public void addCourseActivity(CourseActivity ca) {	
    	em.persist(ca);
    }
    
    public void updateCourseStatus(CoursePK c, int active) {
    	Course tmp = em.find(Course.class, c);
    	if(tmp != null) {
    		tmp.setActive(active);
    	}
    }
    
    public void deleteCourse(CoursePK c) {
    	Course tmp = em.find(Course.class, c);
    	if(tmp!=null) {
    		em.remove(tmp);
    	}
    }

}
