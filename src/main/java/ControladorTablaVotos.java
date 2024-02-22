import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ControladorTablaVotos")
public class ControladorTablaVotos extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ModeloDatos modeloDatos = new ModeloDatos();
        modeloDatos.abrirConexion();
        
        // Obtener los datos de los jugadores y sus votos
        List<String> jugadores = modeloDatos.getJugadores();
        List<Integer> votos = new ArrayList<>();
        for(String jugador : jugadores) {
            votos.add(modeloDatos.getVotos(jugador));
        }
        
        System.out.println("Jugadores: " + jugadores);
        System.out.println("Votos: " + votos);

        modeloDatos.cerrarConexion();
  
        request.setAttribute("jugadores", jugadores);
        request.setAttribute("votos", votos);
        request.getRequestDispatcher("TablaTotalVotos.jsp").forward(request, response);
    }
}
