package pageObjects.anasayfa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TopFrame {

    public WebDriver driver;
    //anasayfa üst menüde yer alan "spor,günaydın,apara,roza,memurlar,kobi,sabahtv,canlıyayın" elemanları
    By navTopMenu =By.xpath("//div[@class='extra-navwrapper']/header/div[@class='topFrame']/nav[@class='menuTop']/ul/li[not(contains(@class,'menuDrop'))]/a");

    //anasayfa üst menüde navobile-dekstop only alanı , içerisinde "-,sondakika,gündem,ekonomi,yaşam,sağlık,dünya,seyahat,yazarlar vb..." elemanları toplam 11 adet
    By navobileDesktopMenu = By.xpath("//div[@class='extra-navwrapper']/header/div[@class='topFrame']/nav[@class='menu navobile-desktop-only']/ul/li[not(contains(@class,'desktop-none'))]/a");

    //currency bar
    By currencyBar = By.xpath("//div[@class='userPrivate boxShadowSet vkatilim-custom show']/ul/li/a");

    /* Constructor */
    public TopFrame(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getNavTopMenuList(){
        return driver.findElements(navTopMenu);
    }

    public List<WebElement> getNavobileDesktopMenuList(){
        return driver.findElements(navobileDesktopMenu);
    }

    public List<WebElement> getCurrencyBarList(){
        return driver.findElements(currencyBar);
    }




}
