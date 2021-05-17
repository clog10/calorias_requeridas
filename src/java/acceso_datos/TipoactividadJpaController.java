/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acceso_datos;

import acceso_datos.exceptions.NonexistentEntityException;
import acceso_datos.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import modelo.Indicadoressalud;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import modelo.Tipoactividad;
import modelo.Usuario;

/**
 *
 * @author Carlos Loaeza
 */
public class TipoactividadJpaController implements Serializable {

    public TipoactividadJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tipoactividad tipoactividad) throws RollbackFailureException, Exception {
        if (tipoactividad.getIndicadoressaludList() == null) {
            tipoactividad.setIndicadoressaludList(new ArrayList<Indicadoressalud>());
        }
        if (tipoactividad.getUsuarioList() == null) {
            tipoactividad.setUsuarioList(new ArrayList<Usuario>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<Indicadoressalud> attachedIndicadoressaludList = new ArrayList<Indicadoressalud>();
            for (Indicadoressalud indicadoressaludListIndicadoressaludToAttach : tipoactividad.getIndicadoressaludList()) {
                indicadoressaludListIndicadoressaludToAttach = em.getReference(indicadoressaludListIndicadoressaludToAttach.getClass(), indicadoressaludListIndicadoressaludToAttach.getIdindsalud());
                attachedIndicadoressaludList.add(indicadoressaludListIndicadoressaludToAttach);
            }
            tipoactividad.setIndicadoressaludList(attachedIndicadoressaludList);
            List<Usuario> attachedUsuarioList = new ArrayList<Usuario>();
            for (Usuario usuarioListUsuarioToAttach : tipoactividad.getUsuarioList()) {
                usuarioListUsuarioToAttach = em.getReference(usuarioListUsuarioToAttach.getClass(), usuarioListUsuarioToAttach.getIdusuario());
                attachedUsuarioList.add(usuarioListUsuarioToAttach);
            }
            tipoactividad.setUsuarioList(attachedUsuarioList);
            em.persist(tipoactividad);
            for (Indicadoressalud indicadoressaludListIndicadoressalud : tipoactividad.getIndicadoressaludList()) {
                Tipoactividad oldTipoactOfIndicadoressaludListIndicadoressalud = indicadoressaludListIndicadoressalud.getTipoact();
                indicadoressaludListIndicadoressalud.setTipoact(tipoactividad);
                indicadoressaludListIndicadoressalud = em.merge(indicadoressaludListIndicadoressalud);
                if (oldTipoactOfIndicadoressaludListIndicadoressalud != null) {
                    oldTipoactOfIndicadoressaludListIndicadoressalud.getIndicadoressaludList().remove(indicadoressaludListIndicadoressalud);
                    oldTipoactOfIndicadoressaludListIndicadoressalud = em.merge(oldTipoactOfIndicadoressaludListIndicadoressalud);
                }
            }
            for (Usuario usuarioListUsuario : tipoactividad.getUsuarioList()) {
                Tipoactividad oldTipoactOfUsuarioListUsuario = usuarioListUsuario.getTipoact();
                usuarioListUsuario.setTipoact(tipoactividad);
                usuarioListUsuario = em.merge(usuarioListUsuario);
                if (oldTipoactOfUsuarioListUsuario != null) {
                    oldTipoactOfUsuarioListUsuario.getUsuarioList().remove(usuarioListUsuario);
                    oldTipoactOfUsuarioListUsuario = em.merge(oldTipoactOfUsuarioListUsuario);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tipoactividad tipoactividad) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Tipoactividad persistentTipoactividad = em.find(Tipoactividad.class, tipoactividad.getIdtact());
            List<Indicadoressalud> indicadoressaludListOld = persistentTipoactividad.getIndicadoressaludList();
            List<Indicadoressalud> indicadoressaludListNew = tipoactividad.getIndicadoressaludList();
            List<Usuario> usuarioListOld = persistentTipoactividad.getUsuarioList();
            List<Usuario> usuarioListNew = tipoactividad.getUsuarioList();
            List<Indicadoressalud> attachedIndicadoressaludListNew = new ArrayList<Indicadoressalud>();
            for (Indicadoressalud indicadoressaludListNewIndicadoressaludToAttach : indicadoressaludListNew) {
                indicadoressaludListNewIndicadoressaludToAttach = em.getReference(indicadoressaludListNewIndicadoressaludToAttach.getClass(), indicadoressaludListNewIndicadoressaludToAttach.getIdindsalud());
                attachedIndicadoressaludListNew.add(indicadoressaludListNewIndicadoressaludToAttach);
            }
            indicadoressaludListNew = attachedIndicadoressaludListNew;
            tipoactividad.setIndicadoressaludList(indicadoressaludListNew);
            List<Usuario> attachedUsuarioListNew = new ArrayList<Usuario>();
            for (Usuario usuarioListNewUsuarioToAttach : usuarioListNew) {
                usuarioListNewUsuarioToAttach = em.getReference(usuarioListNewUsuarioToAttach.getClass(), usuarioListNewUsuarioToAttach.getIdusuario());
                attachedUsuarioListNew.add(usuarioListNewUsuarioToAttach);
            }
            usuarioListNew = attachedUsuarioListNew;
            tipoactividad.setUsuarioList(usuarioListNew);
            tipoactividad = em.merge(tipoactividad);
            for (Indicadoressalud indicadoressaludListOldIndicadoressalud : indicadoressaludListOld) {
                if (!indicadoressaludListNew.contains(indicadoressaludListOldIndicadoressalud)) {
                    indicadoressaludListOldIndicadoressalud.setTipoact(null);
                    indicadoressaludListOldIndicadoressalud = em.merge(indicadoressaludListOldIndicadoressalud);
                }
            }
            for (Indicadoressalud indicadoressaludListNewIndicadoressalud : indicadoressaludListNew) {
                if (!indicadoressaludListOld.contains(indicadoressaludListNewIndicadoressalud)) {
                    Tipoactividad oldTipoactOfIndicadoressaludListNewIndicadoressalud = indicadoressaludListNewIndicadoressalud.getTipoact();
                    indicadoressaludListNewIndicadoressalud.setTipoact(tipoactividad);
                    indicadoressaludListNewIndicadoressalud = em.merge(indicadoressaludListNewIndicadoressalud);
                    if (oldTipoactOfIndicadoressaludListNewIndicadoressalud != null && !oldTipoactOfIndicadoressaludListNewIndicadoressalud.equals(tipoactividad)) {
                        oldTipoactOfIndicadoressaludListNewIndicadoressalud.getIndicadoressaludList().remove(indicadoressaludListNewIndicadoressalud);
                        oldTipoactOfIndicadoressaludListNewIndicadoressalud = em.merge(oldTipoactOfIndicadoressaludListNewIndicadoressalud);
                    }
                }
            }
            for (Usuario usuarioListOldUsuario : usuarioListOld) {
                if (!usuarioListNew.contains(usuarioListOldUsuario)) {
                    usuarioListOldUsuario.setTipoact(null);
                    usuarioListOldUsuario = em.merge(usuarioListOldUsuario);
                }
            }
            for (Usuario usuarioListNewUsuario : usuarioListNew) {
                if (!usuarioListOld.contains(usuarioListNewUsuario)) {
                    Tipoactividad oldTipoactOfUsuarioListNewUsuario = usuarioListNewUsuario.getTipoact();
                    usuarioListNewUsuario.setTipoact(tipoactividad);
                    usuarioListNewUsuario = em.merge(usuarioListNewUsuario);
                    if (oldTipoactOfUsuarioListNewUsuario != null && !oldTipoactOfUsuarioListNewUsuario.equals(tipoactividad)) {
                        oldTipoactOfUsuarioListNewUsuario.getUsuarioList().remove(usuarioListNewUsuario);
                        oldTipoactOfUsuarioListNewUsuario = em.merge(oldTipoactOfUsuarioListNewUsuario);
                    }
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoactividad.getIdtact();
                if (findTipoactividad(id) == null) {
                    throw new NonexistentEntityException("The tipoactividad with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Tipoactividad tipoactividad;
            try {
                tipoactividad = em.getReference(Tipoactividad.class, id);
                tipoactividad.getIdtact();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoactividad with id " + id + " no longer exists.", enfe);
            }
            List<Indicadoressalud> indicadoressaludList = tipoactividad.getIndicadoressaludList();
            for (Indicadoressalud indicadoressaludListIndicadoressalud : indicadoressaludList) {
                indicadoressaludListIndicadoressalud.setTipoact(null);
                indicadoressaludListIndicadoressalud = em.merge(indicadoressaludListIndicadoressalud);
            }
            List<Usuario> usuarioList = tipoactividad.getUsuarioList();
            for (Usuario usuarioListUsuario : usuarioList) {
                usuarioListUsuario.setTipoact(null);
                usuarioListUsuario = em.merge(usuarioListUsuario);
            }
            em.remove(tipoactividad);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tipoactividad> findTipoactividadEntities() {
        return findTipoactividadEntities(true, -1, -1);
    }

    public List<Tipoactividad> findTipoactividadEntities(int maxResults, int firstResult) {
        return findTipoactividadEntities(false, maxResults, firstResult);
    }

    private List<Tipoactividad> findTipoactividadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Tipoactividad as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Tipoactividad findTipoactividad(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tipoactividad.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoactividadCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Tipoactividad as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
