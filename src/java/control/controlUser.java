/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import acceso_datos.TipoactividadJpaController;
import acceso_datos.UsuarioJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import modelo.Usuario;
/**
 *
 * @author Carlos Loaeza
 */
public class controlUser extends HttpServlet {

    @PersistenceUnit
    private EntityManagerFactory emf = null;
    @Resource
    private UserTransaction utx;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            emf = Persistence.createEntityManagerFactory("calorias_requeridasPU");
            
            String nombre = request.getParameter("nombre");
            String s = request.getParameter("sexo");
            char sexo = s.charAt(0);
            String date1 = request.getParameter("fecha_reg");
            Date fecha_reg = Date.valueOf(date1);
            String date2 = request.getParameter("fecha_nac");
            Date fecha_nac = Date.valueOf(date2);
            int acti = Integer.parseInt(request.getParameter("actividad"));
            String usuario = request.getParameter("usuario");
            String contrasenia = request.getParameter("contrasenia");
            
            UsuarioJpaController user;
            user = new UsuarioJpaController(utx, emf);
            
            Usuario u = new Usuario();
            u.setNombre(nombre);
            u.setSexo(sexo);
            u.setFecharegistro(fecha_reg);
            u.setFechanacimiento(fecha_nac);
            u.setUsuario(usuario);
            u.setContrasenia(contrasenia);
            
            TipoactividadJpaController controlTA;
            controlTA = new TipoactividadJpaController(utx,emf);
            u.setTipoact(controlTA.findTipoactividad(acti));
            
            user.create(u);
            RequestDispatcher calculo = request.getRequestDispatcher("ingresar_indicadores.jsp");
            request.setAttribute("actividad", acti);
            request.setAttribute("nombre",nombre);     
            calculo.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(controlUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(controlUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(controlUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(controlUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
