/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acceso_datos;

import acceso_datos.exceptions.NonexistentEntityException;
import acceso_datos.exceptions.RollbackFailureException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.transaction.UserTransaction;
import modelo.Indicadoressalud;
import modelo.Tipoactividad;
import modelo.Usuario;

/**
 *
 * @author Carlos Loaeza
 */
public class IndicadoressaludJpaController extends JpaControladora implements Serializable {

    public IndicadoressaludJpaController(UserTransaction utx, EntityManagerFactory emf) {
        super(utx, emf, Indicadoressalud.class);
    }

    
    @Override
    public void create(Object entidad) throws RollbackFailureException, Exception {
        Indicadoressalud indicadoressalud = (Indicadoressalud) entidad;
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Tipoactividad tipoact = indicadoressalud.getTipoact();
            if (tipoact != null) {
                tipoact = em.getReference(tipoact.getClass(), tipoact.getIdtact());
                indicadoressalud.setTipoact(tipoact);
            }
            Usuario idusuario = indicadoressalud.getIdusuario();
            if (idusuario != null) {
                idusuario = em.getReference(idusuario.getClass(), idusuario.getIdusuario());
                indicadoressalud.setIdusuario(idusuario);
            }
            em.persist(indicadoressalud);
            if (tipoact != null) {
                tipoact.getIndicadoressaludList().add(indicadoressalud);
                tipoact = em.merge(tipoact);
            }
            if (idusuario != null) {
                idusuario.getIndicadoressaludList().add(indicadoressalud);
                idusuario = em.merge(idusuario);
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

    @Override
    public void edit(Object entidad) throws NonexistentEntityException, RollbackFailureException, Exception {
        Indicadoressalud indicadoressalud = (Indicadoressalud) entidad;
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Indicadoressalud persistentIndicadoressalud = em.find(Indicadoressalud.class, indicadoressalud.getIdindsalud());
            Tipoactividad tipoactOld = persistentIndicadoressalud.getTipoact();
            Tipoactividad tipoactNew = indicadoressalud.getTipoact();
            Usuario idusuarioOld = persistentIndicadoressalud.getIdusuario();
            Usuario idusuarioNew = indicadoressalud.getIdusuario();
            if (tipoactNew != null) {
                tipoactNew = em.getReference(tipoactNew.getClass(), tipoactNew.getIdtact());
                indicadoressalud.setTipoact(tipoactNew);
            }
            if (idusuarioNew != null) {
                idusuarioNew = em.getReference(idusuarioNew.getClass(), idusuarioNew.getIdusuario());
                indicadoressalud.setIdusuario(idusuarioNew);
            }
            indicadoressalud = em.merge(indicadoressalud);
            if (tipoactOld != null && !tipoactOld.equals(tipoactNew)) {
                tipoactOld.getIndicadoressaludList().remove(indicadoressalud);
                tipoactOld = em.merge(tipoactOld);
            }
            if (tipoactNew != null && !tipoactNew.equals(tipoactOld)) {
                tipoactNew.getIndicadoressaludList().add(indicadoressalud);
                tipoactNew = em.merge(tipoactNew);
            }
            if (idusuarioOld != null && !idusuarioOld.equals(idusuarioNew)) {
                idusuarioOld.getIndicadoressaludList().remove(indicadoressalud);
                idusuarioOld = em.merge(idusuarioOld);
            }
            if (idusuarioNew != null && !idusuarioNew.equals(idusuarioOld)) {
                idusuarioNew.getIndicadoressaludList().add(indicadoressalud);
                idusuarioNew = em.merge(idusuarioNew);
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
                Integer id = indicadoressalud.getIdindsalud();
                if (findEntity(id) == null) {
                    throw new NonexistentEntityException("The indicadoressalud with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Indicadoressalud indicadoressalud;
            try {
                indicadoressalud = em.getReference(Indicadoressalud.class, id);
                indicadoressalud.getIdindsalud();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The indicadoressalud with id " + id + " no longer exists.", enfe);
            }
            Tipoactividad tipoact = indicadoressalud.getTipoact();
            if (tipoact != null) {
                tipoact.getIndicadoressaludList().remove(indicadoressalud);
                tipoact = em.merge(tipoact);
            }
            Usuario idusuario = indicadoressalud.getIdusuario();
            if (idusuario != null) {
                idusuario.getIndicadoressaludList().remove(indicadoressalud);
                idusuario = em.merge(idusuario);
            }
            em.remove(indicadoressalud);
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

    @Override
    public Indicadoressalud findEntity(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Indicadoressalud.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getEntityCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Indicadoressalud as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
