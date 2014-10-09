/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOTests;

import java.util.Calendar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author xvalusek
 */
public class MachineDAOTest {

   @PersistenceUnit
	public EntityManagerFactory emf;
   
   @BeforeMethod
	public void setup(){
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Calendar cal = Calendar.getInstance();

		em.getTransaction().commit();
		em.close();
	}
    @Test
    public void testPersist() {
   
    }
    
    
}
