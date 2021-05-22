/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import acceso_datos.IndicadoressaludJpaController;
import acceso_datos.JpaControladora;
import acceso_datos.TipoactividadJpaController;
import acceso_datos.UsuarioJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

/**
 *
 * @author Carlos Loaeza
 */
@WebServlet(name = "MiServlet", urlPatterns = {"/MiServlet"})
public abstract class MiServlet extends HttpServlet {

    @PersistenceUnit
    protected EntityManagerFactory emf = Persistence.createEntityManagerFactory("calorias_requeridasPU");
    @Resource
    protected UserTransaction utx;
    protected UsuarioJpaController user;
    protected TipoactividadJpaController controlTA;
    protected IndicadoressaludJpaController indicador;
    
    public abstract void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    public abstract JpaControladora crearControlador();
    
}
