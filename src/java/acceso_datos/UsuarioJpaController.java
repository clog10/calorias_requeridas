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
import modelo.Tipoactividad;
import modelo.Indicadoressalud;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import modelo.Usuario;

/**
 *
 * @author Carlos Loaeza
 */
public class UsuarioJpaController extends JpaControladora implements Serializable {

    public UsuarioJpaController(UserTransaction utx, EntityManagerFactory emf) {
        super(utx, emf, Usuario.class);
    }

    @Override
    public void create(Object entidad) throws RollbackFailureException, Exception {
        Usuario usuario = (Usuario) entidad;
        if (usuario.getIndicadoressaludList() == null) {
            usuario.setIndicadoressaludList(new ArrayList<Indicadoressalud>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Tipoactividad tipoact = usuario.getTipoact();
            if (tipoact != null) {
                tipoact = em.getReference(tipoact.getClass(), tipoact.getIdtact());
                usuario.setTipoact(tipoact);
            }
            List<Indicadoressalud> attachedIndicadoressaludList = new ArrayList<Indicadoressalud>();
            for (Indicadoressalud indicadoressaludListIndicadoressaludToAttach : usuario.getIndicadoressaludList()) {
                indicadoressaludListIndicadoressaludToAttach = em.getReference(indicadoressaludListIndicadoressaludToAttach.getClass(), indicadoressaludListIndicadoressaludToAttach.getIdindsalud());
                attachedIndicadoressaludList.add(indicadoressaludListIndicadoressaludToAttach);
            }
            usuario.setIndicadoressaludList(attachedIndicadoressaludList);
            em.persist(usuario);
            if (tipoact != null) {
                tipoact.getUsuarioList().add(usuario);
                tipoact = em.merge(tipoact);
            }
            for (Indicadoressalud indicadoressaludListIndicadoressalud : usuario.getIndicadoressaludList()) {
                Usuario oldIdusuarioOfIndicadoressaludListIndicadoressalud = indicadoressaludListIndicadoressalud.getIdusuario();
                indicadoressaludListIndicadoressalud.setIdusuario(usuario);
                indicadoressaludListIndicadoressalud = em.merge(indicadoressaludListIndicadoressalud);
                if (oldIdusuarioOfIndicadoressaludListIndicadoressalud != null) {
                    oldIdusuarioOfIndicadoressaludListIndicadoressalud.getIndicadoressaludList().remove(indicadoressaludListIndicadoressalud);
                    oldIdusuarioOfIndicadoressaludListIndicadoressalud = em.merge(oldIdusuarioOfIndicadoressaludListIndicadoressalud);
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

    @Override
    public void edit(Object entidad) throws NonexistentEntityException, RollbackFailureException, Exception {
        Usuario usuario = (Usuario) entidad;
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getIdusuario());
            Tipoactividad tipoactOld = persistentUsuario.getTipoact();
            Tipoactividad tipoactNew = usuario.getTipoact();
            List<Indicadoressalud> indicadoressaludListOld = persistentUsuario.getIndicadoressaludList();
            List<Indicadoressalud> indicadoressaludListNew = usuario.getIndicadoressaludList();
            if (tipoactNew != null) {
                tipoactNew = em.getReference(tipoactNew.getClass(), tipoactNew.getIdtact());
                usuario.setTipoact(tipoactNew);
            }
            List<Indicadoressalud> attachedIndicadoressaludListNew = new ArrayList<Indicadoressalud>();
            for (Indicadoressalud indicadoressaludListNewIndicadoressaludToAttach : indicadoressaludListNew) {
                indicadoressaludListNewIndicadoressaludToAttach = em.getReference(indicadoressaludListNewIndicadoressaludToAttach.getClass(), indicadoressaludListNewIndicadoressaludToAttach.getIdindsalud());
                attachedIndicadoressaludListNew.add(indicadoressaludListNewIndicadoressaludToAttach);
            }
            indicadoressaludListNew = attachedIndicadoressaludListNew;
            usuario.setIndicadoressaludList(indicadoressaludListNew);
            usuario = em.merge(usuario);
            if (tipoactOld != null && !tipoactOld.equals(tipoactNew)) {
                tipoactOld.getUsuarioList().remove(usuario);
                tipoactOld = em.merge(tipoactOld);
            }
            if (tipoactNew != null && !tipoactNew.equals(tipoactOld)) {
                tipoactNew.getUsuarioList().add(usuario);
                tipoactNew = em.merge(tipoactNew);
            }
            for (Indicadoressalud indicadoressaludListOldIndicadoressalud : indicadoressaludListOld) {
                if (!indicadoressaludListNew.contains(indicadoressaludListOldIndicadoressalud)) {
                    indicadoressaludListOldIndicadoressalud.setIdusuario(null);
                    indicadoressaludListOldIndicadoressalud = em.merge(indicadoressaludListOldIndicadoressalud);
                }
            }
            for (Indicadoressalud indicadoressaludListNewIndicadoressalud : indicadoressaludListNew) {
                if (!indicadoressaludListOld.contains(indicadoressaludListNewIndicadoressalud)) {
                    Usuario oldIdusuarioOfIndicadoressaludListNewIndicadoressalud = indicadoressaludListNewIndicadoressalud.getIdusuario();
                    indicadoressaludListNewIndicadoressalud.setIdusuario(usuario);
                    indicadoressaludListNewIndicadoressalud = em.merge(indicadoressaludListNewIndicadoressalud);
                    if (oldIdusuarioOfIndicadoressaludListNewIndicadoressalud != null && !oldIdusuarioOfIndicadoressaludListNewIndicadoressalud.equals(usuario)) {
                        oldIdusuarioOfIndicadoressaludListNewIndicadoressalud.getIndicadoressaludList().remove(indicadoressaludListNewIndicadoressalud);
                        oldIdusuarioOfIndicadoressaludListNewIndicadoressalud = em.merge(oldIdusuarioOfIndicadoressaludListNewIndicadoressalud);
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
                Integer id = usuario.getIdusuario();
                if (findEntity(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getIdusuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            Tipoactividad tipoact = usuario.getTipoact();
            if (tipoact != null) {
                tipoact.getUsuarioList().remove(usuario);
                tipoact = em.merge(tipoact);
            }
            List<Indicadoressalud> indicadoressaludList = usuario.getIndicadoressaludList();
            for (Indicadoressalud indicadoressaludListIndicadoressalud : indicadoressaludList) {
                indicadoressaludListIndicadoressalud.setIdusuario(null);
                indicadoressaludListIndicadoressalud = em.merge(indicadoressaludListIndicadoressalud);
            }
            em.remove(usuario);
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
    public Usuario findEntity(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getEntityCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Usuario as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
