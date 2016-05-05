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

}
