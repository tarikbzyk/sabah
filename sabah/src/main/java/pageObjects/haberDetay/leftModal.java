package pageObjects.haberDetay;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class leftModal {
    public WebDriver driver;
    By pageTitle = By.xpath("//body/section[@class='contentFrame']/div/div/div[1]/div/div[3]/h1");
    By girisTarihi = By.xpath("//body/section[@class='contentFrame']/div/div/div[1]/div/div[2]/div/span/span[1]");
    By guncellemeTarihi = By.xpath("//body/section[@class='contentFrame']/div/div/div[1]/div/div[2]/div/span/span[2]");

    public leftModal(WebDriver driver){
        this.driver=driver;
    }

    public WebElement getPageTitle(){
        return driver.findElement(pageTitle);
    }
}
