
import java.sql.DriverManager;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BaseDao {

    private static EntityManagerFactory emf;

    public static EntityManager getEntityManager() {

        if (emf == null) {

            emf = Persistence.createEntityManagerFactory("stroje");
        }

        return emf.createEntityManager();
    }
}