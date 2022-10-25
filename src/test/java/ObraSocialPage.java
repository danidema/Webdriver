import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ObraSocialPage extends PageObject{

    @FindBy(xpath = "//h3[contains(text(),'Bienvenido a OSTH On-Line')]")
    private WebElement bienvenido_label;

    @FindBy(id = "estado")
    private WebElement estado;

    public ObraSocialPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle(){
        return this.bienvenido_label.getText();
    }

    public String getEstado(){
        return this.estado.getText();
    }
}
