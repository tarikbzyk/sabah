package pageObjects.anasayfa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Manset {

    public WebDriver driver;

    By Manset20li = By.xpath("//section[@class='homeContent contentFrame']/div/div[4]/div[1]/div/div/ul/ul/li/a");
    By MansetYanı4lu = By.xpath("//section[@class='homeContent contentFrame']/div/div[4]/div[2]/div/div/figure/a");
    By MansetAltı5li = By.xpath("//section[@class='homeContent contentFrame']/div/div[5]/div/div/ul/li/figure/a");

    public Manset(WebDriver driver){
        this.driver=driver;
    }

    public List<WebElement> getManset20liList(){
        return driver.findElements(Manset20li);
    }

    public List<WebElement> getMansetyanı4luList(){
        return driver.findElements(MansetYanı4lu);
    }

    public List<WebElement> getMansetAlti5liList(){
        return driver.findElements(MansetAltı5li);
    }







}
