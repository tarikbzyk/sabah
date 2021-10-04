package pageObjects.anasayfa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MansetAltı {

    public WebDriver driver;

    By MansetAltıToplu = By.xpath("//figure[contains(@class,'borderBox')]/a");


    public MansetAltı(WebDriver driver){
        this.driver=driver;
    }

    public List<WebElement> getMansetAltıTopluListe(){
        return driver.findElements(MansetAltıToplu);
    }



}