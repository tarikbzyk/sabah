package pageObjects.anasayfa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Surmanset {

    public WebDriver driver;

    By surmanset5li = By.xpath("//section[@class='homeContent contentFrame']/div/div[1]/div/div/div/ul/ul/li/a");
    By surmansetAltı4lu = By.xpath("//section[@class='homeContent contentFrame']/div/div[3]/div/figure/a");

    /*Constructor*/
    public Surmanset(WebDriver driver){
        this.driver=driver;
    }

    public List<WebElement> getSurmansetList(){
        return driver.findElements(surmanset5li);
    }

    public List<WebElement> getSurmansetAltı4lu(){
        return driver.findElements(surmansetAltı4lu);
    }

}
