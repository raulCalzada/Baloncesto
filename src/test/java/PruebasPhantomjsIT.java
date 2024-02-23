import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

public class PruebasPhantomjsIT {
    private static WebDriver driver = null;

    @Test
    public void tituloIndexTest() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);

        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,"/usr/bin/phantomjs");

        caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, new String[] {"--web-security=no", "--ignore-ssl-errors=yes"});
      

        driver = new PhantomJSDriver(caps);
        driver.navigate().to("http://localhost:8080/Baloncesto/");
        
        assertEquals("Votacion mejor jugador liga ACB", driver.getTitle(), "El titulo no es correcto");

        System.out.println(driver.getTitle());
        
        driver.close();
        driver.quit();
    }

    @Test
    public void ponerVotosACeroYVerificar() {
        // Abre la página principal
        driver.navigate().to("http://localhost:8080/Baloncesto/");

        // Pulsar el botón "Poner votos a cero"
        driver.findElement(By.name("B2")).click();

        // Pulsar el botón "Ver votos"
        driver.findElement(By.xpath("//button[contains(text(),'Tabla Votos')]")).click();

        // Comprobar que los votos para cada jugador son cero en la página "VerVotos.jsp"
        driver.navigate().to("http://localhost:8080/Baloncesto/ControladorTablaVotos");

        // Comprobar que los votos para cada jugador son cero en la página "VerVotos.jsp"
        List<WebElement> elementosVotos = driver.findElements(By.xpath("//table//tr//td[2]"));
        for (WebElement elemento : elementosVotos) {
            assertEquals("0", elemento.getText(), "El voto no es cero");
        }

    }
}
