import java.sql.*;
import java.util.List;

import scorex.util.ArrayList;

public class ModeloDatos {

    private Connection con;
    private Statement set;
    private ResultSet rs;

    public void abrirConexion() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Con variables de entorno
            String dbHost = System.getenv().get("DATABASE_HOST");
            String dbPort = System.getenv().get("DATABASE_PORT");
            String dbName = System.getenv().get("DATABASE_NAME");
            String dbUser = System.getenv().get("DATABASE_USER");
            String dbPass = System.getenv().get("DATABASE_PASS");

            String url = dbHost + ":" + dbPort + "/" + dbName;
            con = DriverManager.getConnection(url, dbUser, dbPass);

        } catch (Exception e) {
            // No se ha conectado       
        }
    }

    public boolean existeJugador(String nombre) {
        boolean existe = false;
        String cad;
        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM Jugadores");
            while (rs.next()) {
                cad = rs.getString("Nombre");
                cad = cad.trim();
                if (cad.compareTo(nombre.trim()) == 0) {
                    existe = true;
                }
            }
            rs.close();
            set.close();
        } catch (Exception e) {
             // Ignorar excepción porque no es crítica en este contexto
            // No necesitamos tomar medidas adicionales ya que no afecta al flujo principal
        }
        return (existe);
    }

    public void actualizarJugador(String nombre) {
        try {
            set = con.createStatement();
            set.executeUpdate("UPDATE Jugadores SET votos=votos+1 WHERE nombre " + " LIKE '%" + nombre + "%'");
            rs.close();
            set.close();
        } catch (Exception e) {
         // Ignorar excepción porque no es crítica en este contexto
            // No necesitamos tomar medidas adicionales ya que no afecta al flujo principal
        }
    }

    public void insertarJugador(String nombre) {
        try {
            set = con.createStatement();
            set.executeUpdate("INSERT INTO Jugadores " + " (nombre,votos) VALUES ('" + nombre + "',1)");
            rs.close();
            set.close();
        } catch (Exception e) {
     // Ignorar excepción porque no es crítica en este contexto
            // No necesitamos tomar medidas adicionales ya que no afecta al flujo principal
        }
    }

    public int getVotos(String nombre) {
        int votos = 0;
        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT votos FROM Jugadores WHERE nombre = '" + nombre + "'");
            if (rs.next()) {
                votos = rs.getInt("votos");
            }
            rs.close();
            set.close();
        } catch (Exception e) {
            votos = 0;
        }
        return votos;
    }

    public List<String> getJugadores(){
        List<String> jugadores = new ArrayList<String>();
        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT nombre FROM Jugadores");
            while (rs.next()) {
                jugadores.add(rs.getString("nombre"));
            }
            rs.close();
            set.close();
        } catch (Exception e) {
            jugadores.add("Raul");
            jugadores.add(e.getMessage());
        }
        return jugadores;
    }

    public void resetearVotos() {
        try {
            set = con.createStatement();
            set.executeUpdate("UPDATE Jugadores SET votos=0");
            rs.close();
            set.close();
        } catch (Exception e) {
        }
    }

    public void cerrarConexion() {
        try {
            con.close();
        } catch (Exception e) {
             // Ignorar excepción porque no es crítica en este contexto
        }
    }

}
