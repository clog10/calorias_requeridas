
package control;

import acceso_datos.IndicadoressaludJpaController;
import acceso_datos.JpaControladora;
import acceso_datos.TipoactividadJpaController;
import acceso_datos.UsuarioJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import modelo.Indicadoressalud;
import modelo.Usuario;

/**
 *
 * @author Carlos Loaeza
 */
public class controlIS extends MiServlet {
    
    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            String date = request.getParameter("fecha");
            Date fecha = Date.valueOf(date);
            double estatura = Double.parseDouble(request.getParameter("estatura"));
            double peso = Double.parseDouble(request.getParameter("peso"));
            int cintura = Integer.parseInt(request.getParameter("cintura"));
            int cadera = Integer.parseInt(request.getParameter("cadera"));
            int acti = Integer.parseInt(request.getParameter("actividad"));

            indicador = (IndicadoressaludJpaController) crearControlador();
            user = new UsuarioJpaController(utx, emf);
            controlTA = new TipoactividadJpaController(utx, emf);

            List<Usuario> u = user.findEntities();
            Usuario usuario = u.get(u.size() - 1);

            Indicadoressalud indicadores = new Indicadoressalud();
            indicadores.setFecha(fecha);
            indicadores.setCadera(cadera);
            indicadores.setEstatura(estatura);
            indicadores.setPeso(peso);
            indicadores.setCintura(cintura);
            indicadores.setTipoact(controlTA.findEntity(acti));
            indicadores.setIdusuario(usuario);

            String nombre = usuario.getNombre();
            double est = indicadores.getEstatura() / 100;
            double imc = indicadores.getPeso() / (est * est);
            double icc = indicadores.getCintura() / indicadores.getCadera();
            indicador.create(indicadores);
            RequestDispatcher calculo = request.getRequestDispatcher("resultados.jsp");
            request.setAttribute("nombre", nombre);
            request.setAttribute("imc", imc);
            request.setAttribute("icc", icc);
            calculo.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(controlIS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public JpaControladora crearControlador() {
         return new IndicadoressaludJpaController(utx, emf);
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

}
