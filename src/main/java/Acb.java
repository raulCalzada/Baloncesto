
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Acb extends HttpServlet {

    private ModeloDatos bd;

    @Override
    public void init(ServletConfig cfg) throws ServletException {
        bd = new ModeloDatos();
        bd.abrirConexion();
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession s = req.getSession(true);
        String nombreP = req.getParameter("txtNombre");
        String nombre = req.getParameter("R1");
        String reset = req.getParameter("B2");
    
        if ("true".equals(reset)) {
            try {
                bd.resetearVotos();
            } catch (Exception e) {
                // Manejar la excepción si es necesario
            }
            return;
        }
    
        if (nombre != null) {
            if (nombre.equals("Otros")) {
                nombre = req.getParameter("txtOtros");
            }
            if (bd.existeJugador(nombre)) {
                bd.actualizarJugador(nombre);
            } else {
                bd.insertarJugador(nombre);
            }
        }
        
        s.setAttribute("nombreCliente", nombreP);
        res.sendRedirect(res.encodeRedirectURL("TablaVotos.jsp"));
    }

    @Override
    public void destroy() {
        bd.cerrarConexion();
        super.destroy();
    }
}