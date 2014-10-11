import Entity.Dao.MachineDao;
import Entity.DaoImpl.MachineDaoImpl;
import Entity.Machine;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
//        new AnnotationConfigApplicationContext(DaoContext.class);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("stroje");
        EntityManager em1 = emf.createEntityManager();

        em1.getTransaction().begin();
        MachineDao machineDao = new MachineDaoImpl();
        Machine machine = new Machine();
        machineDao.persist(machine);
        System.out.println(machine.getId());
        em1.flush();
        em1.getTransaction().commit();
        em1.close();

    }
}
