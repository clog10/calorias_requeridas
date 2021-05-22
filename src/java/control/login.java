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
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;
import modelo.Usuario;

/**
 *
 * @author Carlos Loaeza
 */
public class login extends MiServlet {

    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            RequestDispatcher calculo = request.getRequestDispatcher("");

            String usuario = request.getParameter("usuario");
            String contrasenia = request.getParameter("contrasenia");

            user = (UsuarioJpaController) crearControlador();

            List<Usuario> usuarios = user.findEntities();

            Usuario u;

            int acti = 0;
            String nombre = "";

            String[] users = new String[usuarios.size()];
            String[] contrasenias = new String[usuarios.size()];

            for (int i = 0; i < usuarios.size(); i++) {
                users[i] = usuarios.get(i).getUsuario();
                contrasenias[i] = usuarios.get(i).getContrasenia();
            }

            for (int i = 0; i < users.length; i++) {
                if (usuario.equals(users[i]) && contrasenia.equals(contrasenias[i])) {
                    calculo = request.getRequestDispatcher("ingresar_indicadores.jsp");
                    u = usuarios.get(i);
                    acti = usuarios.get(i).getTipoact().getIdtact();
                    request.setAttribute("actividad", acti);
                    nombre = usuarios.get(i).getNombre();
                    request.setAttribute("nombre", nombre);
                    calculo.forward(request, response);
                    break;
                } else {
                    calculo = request.getRequestDispatcher("index.jsp");
                    calculo.forward(request, response);
                }
            }

        }
    }

    @Override
    public JpaControladora crearControlador() {
        return new UsuarioJpaController(utx, emf);
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
