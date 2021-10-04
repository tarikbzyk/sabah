package com.sabah;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pageObjects.anasayfa.Manset;
import pageObjects.anasayfa.MansetAltı;
import resources.base;

import java.io.IOException;
import java.util.*;


public class utmChecker extends base {

    public WebDriver driver;
    public static Logger log =LogManager.getLogger(base.class.getName());
    SoftAssert softAssert = new SoftAssert();
    Manset m1;
    MansetAltı m2;
    List<WebElement> listeWebElement;
    List<String> windHandler = new ArrayList<>();
    String newTab = Keys.chord(Keys.CONTROL, Keys.ENTER);
    String pathUrl;
    String mansetFirstUrl;
    boolean isPageFound;



    @BeforeTest
    public void initialize() throws IOException
    {
        driver =initializeDriver();
        driver.get(prop.getProperty("url"));
        m1=new Manset(driver);
        m2=new MansetAltı(driver);

    }

    @Test (priority = 0)
    public void galeri_UtmHomePageChecker() throws IOException, InterruptedException {

        mansetFirstUrl = m1.getManset20liList().get(0).getAttribute("href");


        /* anasayfada '/galeri/' uzantısı ile listelenen haberler listeye alınıp biri yeni sekme olarak açılır */
        listeWebElement = m2.getMansetAltıTopluListe();
        List<WebElement> galeriSayfalar = listelemeType1(listeWebElement);
        pathUrl = driver.getCurrentUrl();
        galeriSayfalar.get(1).sendKeys(newTab);


        /* açılan yeni sekme handle edilip işlem yapılabilir hale getirildi. */

        iterate(driver);
        isPageFound = infinityController(driver,pathUrl);

        Assert.assertTrue(isPageFound);

    }

    @Test (priority = 1)
    public void google_UtmHomePageChecker() throws IOException, InterruptedException{

        /*Yeni sekme açılır. Açılan sekmede Google'a gidilir ve tab handle edilir */
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.open('https://www.google.com.tr/', '_blank');");
        windHandler = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windHandler.get(windHandler.size()-1));

        /*Arama çubuğunda arama yapılacak string hazırlanır ve aratılır*/
        mansetFirstUrl = "site:".concat(mansetFirstUrl);
        driver.findElement(By.xpath("//input[@class = 'gLFyf gsfi']")).sendKeys(mansetFirstUrl, Keys.ENTER);
        pathUrl = driver.getCurrentUrl();

        //driver.findElement(By.xpath("//div[@class='eqAnXb']/div[2]/div/div/div/div/div/div/div[1]")).click();
        driver.findElement(By.xpath("//body/div[@class='main']/div/div[@class='GyAeWb']/div[1]/div/div[@class='eqAnXb']/div[2]/div/div/div[1]/div/div/div[1]")).click();
        boolean googleCheck;
        googleCheck = infinityController(driver,pathUrl);

        Assert.assertTrue(googleCheck);

    }

    @AfterTest
    public void teardown(){
        driver.quit();
    }


    private List<WebElement> listelemeType1(List<WebElement> listElement){


        List<WebElement> galeriListe = new ArrayList<>();
        String url;

        for(int i=0;i<listElement.size();i++){

            url= listElement.get(i).getAttribute("href");

            if(url.contains("https://www.sabah.com.tr/galeri/")){
                galeriListe.add(listElement.get(i));
            }

        }

        return galeriListe;
    }


    private static void iterate(WebDriver driver) {
        Set<String> windowHandle = new HashSet<>();
        windowHandle = driver.getWindowHandles();
        Iterator<String> it = windowHandle.iterator();
        it.next();
        driver.switchTo().window(it.next());
    }

    public static boolean infinityController (WebDriver driver, String path) throws InterruptedException {

        List<String> listeUrl = new ArrayList<>();
        Actions action = new Actions(driver);
        String currentURL1 = driver.getCurrentUrl();
        boolean isFound = true;

        while(!(currentURL1.contains("web_anasayfa"))){

            listeUrl.add(driver.getCurrentUrl());
            action.sendKeys(Keys.PAGE_DOWN).build().perform();
            currentURL1 = driver.getCurrentUrl();
            Thread.sleep(500L);

            if (listeUrl.size()>8 && listeUrl.get(listeUrl.size()-1).equals(listeUrl.get(listeUrl.size()-8))){
                isFound=false;
                break;
            }


        }


        if(isFound==true && path.contains("https://www.sabah.com.tr/")){
            System.out.println("Galeri haber sonunda UTM homepage yüklendi. Test case hatasız tamamlandı.");
        }
        else if (isFound==true && path.contains("www.google.com.tr/search")){
            System.out.println("Google'dan açılan haber sonunda UTM homepage yüklendi. Test case hatasız tamamlandı.");
        }
        else if (isFound==false && path.contains("www.sabah.com.tr")){
            System.out.println("Galeri anasayfa sonunda UTM homepage YÜKLENMEDİ !!!");
        }
        else if (isFound==false && path.contains("www.google.com.tr")){
            System.out.println("Google'dan açılan haber sonunda UTM homepage YÜKLENMEDİ !!!");
        }


        return isFound;
    }


}
