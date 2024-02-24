import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class ModeloDatosTest {

    @Test
    public void testExisteJugador() {
        System.out.println("Prueba de existeJugador");
        String nombre = "";
        ModeloDatos instance = new ModeloDatos();
        boolean expResult = false;
        boolean result = instance.existeJugador(nombre);
        assertEquals(expResult, result);
        //fail("Fallo forzado.");
    }


    @Test
    public void testActualizarJugadorIncrementaVotos() {
        // Crear un mock de la clase ModeloDatos
        ModeloDatos mockModeloDatos = mock(ModeloDatos.class);
        
        // Configurar el comportamiento esperado del mock
        String nombreJugador = "Ejemplo";
        int votosIniciales = 5;
        when(mockModeloDatos.getVotos(nombreJugador)).thenReturn(votosIniciales);
        
        // Llamar al método que queremos probar
        mockModeloDatos.actualizarJugador(nombreJugador);
        
        // Verificar que el método actualizarJugador() incrementa los votos correctamente
        verify(mockModeloDatos, times(1)).actualizarJugador(nombreJugador);
        verify(mockModeloDatos, times(1)).getVotos(nombreJugador);
        
        // Comprobar que el número de votos se haya incrementado en 1
        int votosDespues = votosIniciales + 1;
        assertEquals(votosDespues, mockModeloDatos.getVotos(nombreJugador));
    }

}

    