/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import acceso_datos.JpaControladora;
import acceso_datos.TipoactividadJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;
import modelo.Tipoactividad;

/**
 *
 * @author Carlos Loaeza
 */
public class controlTA extends MiServlet {

    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            controlTA = (TipoactividadJpaController) crearControlador();
            List<Tipoactividad> actividades = controlTA.findEntities();
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Actividades</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<center>");
            out.println("<table>");
            out.println("<thead>");
            out.println("<th colspan=\"3\" align=\"center\">Actividades registradas</th>");
            out.println("</thead>");
            out.println("<tbody>");
            
            Iterator iterador = actividades.iterator();
            Tipoactividad actividad;
            while(iterador.hasNext()){
                actividad = (Tipoactividad) iterador.next();
                out.println("<tr>");
                out.println("<td>" + actividad.getIdtact() + "</td>");
                out.print("<td>" + actividad.getDescripcion() + "</td>");
                out.print("<td>" + actividad.getActividades() + "</td>");
                out.println("</tr>");
            }
            
            out.println("</tbody>");
            out.println("</table>");
            out.println("<br>");
            out.println("<a href=\"index.jsp\">Volver al inicio</a>");
            out.println("</center>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    public JpaControladora crearControlador() {
        return new TipoactividadJpaController(utx, emf);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
