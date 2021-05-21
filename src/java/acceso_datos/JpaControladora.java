package acceso_datos;

import acceso_datos.exceptions.NonexistentEntityException;
import acceso_datos.exceptions.RollbackFailureException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.UserTransaction;

/**
 *
 * @author Carlos Loaeza
 */
public abstract class JpaControladora<T> implements Serializable {

    protected UserTransaction utx = null;
    protected EntityManagerFactory emf = null;
    protected Class<T> modelo;

    public JpaControladora(UserTransaction us, EntityManagerFactory em, Class<T> modelo) {
        utx = us;
        emf = em;
        this.modelo = modelo;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public abstract void create(T entidad) throws RollbackFailureException, Exception;

    public abstract void edit(T entidad) throws NonexistentEntityException, RollbackFailureException, Exception;

    public abstract void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception;

    public List<T> findEntities() {
        return findEntities(true, -1, -1);
    }

    public List<T> findEntities(int maxResults, int firstResult) {
        return findEntities(false, maxResults, firstResult);
    }

    private List<T> findEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(this.modelo));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public abstract Object findEntity(Integer id);

    public abstract int getEntityCount();

}
