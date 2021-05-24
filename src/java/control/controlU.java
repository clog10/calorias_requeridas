/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import acceso_datos.JpaControladora;
import acceso_datos.UsuarioJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Usuario;

/**
 *
 * @author Carlos Loaeza
 */
public class controlU extends MiServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            user = (UsuarioJpaController) crearControlador();
            List<Usuario> usuarios = user.findEntities();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Usuarios Registrados</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<center>");
            out.println("<h1>Usuarios</h1>");
            out.println("<table>");
            out.println("<thead>");
            out.println("<th align=\"center\">Id</th>");
            out.println("<th align=\"center\">Nombre</th>");
            out.println("<th align=\"center\">Sexo</th>");
            out.println("<th align=\"center\">Fecha Registro</th>");
            out.println("<th align=\"center\">Usuario</th>");
            out.println("<th align=\"center\">Contrasenia</th>");
            out.println("<th align=\"center\">Fecha Nacimiento</th>");
            out.println("<th align=\"center\">Actividad</th>");
            out.println("</thead>");
            out.println("<tbody>");
            
            Iterator iterador = usuarios.iterator();
            Usuario usuario;
            while(iterador.hasNext()){
                usuario = (Usuario) iterador.next();
                out.println("<tr>");
                out.println("<td>" + usuario.getIdusuario()+ "</td>");
                out.println("<td align=\"center\">"+usuario.getNombre()+"</td>");
                out.println("<td align=\"center\">"+usuario.getSexo()+"</td>");
                out.println("<td align=\"center\">"+new SimpleDateFormat("dd-MM-yyyy").format(usuario.getFecharegistro())+"</td>");
                out.println("<td align=\"center\">"+usuario.getUsuario()+"</td>");
                out.println("<td align=\"center\">"+usuario.getContrasenia()+"</td>");
                out.println("<td align=\"center\">"+new SimpleDateFormat("dd-MM-yyyy").format(usuario.getFechanacimiento())+"</td>");
                out.println("<td align=\"center\">"+usuario.getTipoact().getDescripcion()+"</td>");
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

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    public JpaControladora crearControlador() {
        return new UsuarioJpaController(utx, emf);
    }
}
