package tempustime;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Session Bean implementation class CourseBean
 */
@Stateless
@LocalBean
public class CourseBean {

    @PersistenceContext(name="TempusTime")
    EntityManager em;
	
    public CourseBean() {
    }
    
    public List<Course> getListOfCourses() {
    	TypedQuery<Course> theQuery = em.createQuery("select p from Courses p", Course.class);
    	List<Course> result = theQuery.getResultList();
    	
    	return result;
    }

}
