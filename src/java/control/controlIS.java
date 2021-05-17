/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import acceso_datos.IndicadoressaludJpaController;
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
public class controlIS extends HttpServlet {

    @PersistenceUnit
    private EntityManagerFactory emf = null;
    @Resource
    private UserTransaction utx;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            emf = Persistence.createEntityManagerFactory("calorias_requeridasPU");

            String date = request.getParameter("fecha");
            Date fecha = Date.valueOf(date);
            double estatura = Double.parseDouble(request.getParameter("estatura"));
            double peso = Double.parseDouble(request.getParameter("peso"));
            int cintura = Integer.parseInt(request.getParameter("cintura"));
            int cadera = Integer.parseInt(request.getParameter("cadera"));
            int acti = Integer.parseInt(request.getParameter("actividad"));

            IndicadoressaludJpaController indicador;
            indicador = new IndicadoressaludJpaController(utx, emf);
            UsuarioJpaController user;
            user = new UsuarioJpaController(utx, emf);
            TipoactividadJpaController controlTA;
            controlTA = new TipoactividadJpaController(utx, emf);

            List<Usuario> u = user.findUsuarioEntities();
            Usuario usuario = u.get(u.size() - 1);

            Indicadoressalud indicadores = new Indicadoressalud();
            indicadores.setFecha(fecha);
            indicadores.setCadera(cadera);
            indicadores.setEstatura(estatura);
            indicadores.setPeso(peso);
            indicadores.setCintura(cintura);
            indicadores.setTipoact(controlTA.findTipoactividad(acti));
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
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(controlIS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(controlIS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
