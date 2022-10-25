import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestPlan {
    private static final WebDriver driver = new ChromeDriver();

    @BeforeSuite
    public static void main(String[] args){
        System.setProperty("webdriver.chrome.driver", Utils.CHROME_DRIVER_LOCATION);
    }
    //Caso 1: Intentar ingresar sin escribir usuario ni contraseña.
    //El mensaje esperado debería ser "Atención: Debe ingresar un nombre de usuario"
    @Test(testName = "login without username and password")
    public static void LoginWithoutUsernameAndPassword(){
        driver.get(Utils.BASE_URL);
        LoginForm loginForm = new LoginForm(driver);
        loginForm.enterUsername("");
        loginForm.enterPassword("");
        loginForm.pressLoginButton();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        ObraSocialPage obraSocialPage = new ObraSocialPage(driver);
        Assert.assertEquals(obraSocialPage.getEstado(),"Atención: Debe ingresar un nombre de usuario");
    }

    //Caso 2: Intentar ingresar sólo escribiendo usuario, dejando la contraseña vacía.
    //El mensaje esperado debería ser "Atención: El password no puede estar vacío"
    @Test(testName = "login without password")
    public static void LoginWithoutPassword(){
        driver.get(Utils.BASE_URL);
        LoginForm loginForm = new LoginForm(driver);
        loginForm.enterUsername("dumbridge");
        loginForm.enterPassword("");
        loginForm.pressLoginButton();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        ObraSocialPage obraSocialPage = new ObraSocialPage(driver);
        Assert.assertEquals(obraSocialPage.getEstado(),"Atención: El password no puede estar vacío");
    }

    //Caso 3: Intentar ingresar con el usuario "dumbridge" y la contraseña "tomriddle".
    //Se debería confirmar que se accede a una web donde está el mensaje "Bienvenido a OSTH On-Line"

    @Test(testName = "login successfully")
    public static void LoginSuccessfully(){
        driver.get(Utils.BASE_URL);
        LoginForm loginForm = new LoginForm(driver);
        loginForm.enterUsername("dumbridge");
        loginForm.enterPassword("tomriddle");
        loginForm.pressLoginButton();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        ObraSocialPage obraSocialPage = new ObraSocialPage(driver);
        Assert.assertEquals(obraSocialPage.getTitle(),"Bienvenido a OSTH On-Line");
    }

    //Caso 4: Intentar ingresar con el usuario "dumbridge" y una contraseña incorrecta.
    //El mensaje esperado debería ser "Atención: El password ingresado no es válido".
    @Test(testName = "login with incorrect password")
    public static void LoginWithIncorrectPassword(){
        driver.get(Utils.BASE_URL);
        LoginForm loginForm = new LoginForm(driver);
        loginForm.enterUsername("dumbridge");
        loginForm.enterPassword("123456");
        loginForm.pressLoginButton();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        ObraSocialPage obraSocialPage = new ObraSocialPage(driver);
        Assert.assertEquals(obraSocialPage.getEstado(),"Atención: El password ingresado no es válido");
    }

    @AfterSuite
    public static void cleanUp(){
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
