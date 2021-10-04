package com.sabah;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import pageObjects.anasayfa.Manset;
import pageObjects.anasayfa.MansetAltı;
import pageObjects.haberDetay.leftModal;
import resources.base;

import java.io.IOException;
import java.util.*;

public class deneme extends base {



    public static void main(String[] args) throws IOException, InterruptedException {

        base con = new base();
        WebDriver driver = con.initializeDriver();
        driver.get("https://www.sabah.com.tr");
        List<WebElement> listeWebElement = new ArrayList<>();
        String newTab = Keys.chord(Keys.CONTROL, Keys.ENTER);
        List<String> windHandler = new ArrayList<>();

        /* gerekli xpathları çekmek için bulundukları sınıflardan nesneler tanımlandı */
        leftModal l1 = new leftModal(driver);
        MansetAltı m2 = new MansetAltı(driver);

        /* Anasayfa mansette bulunan 20li haberden birinicisinin url'i alınır.*/
        Manset m1 = new Manset(driver);
        String mansetFirstUrl = m1.getManset20liList().get(0).getAttribute("href");


        /* anasayfada '/galeri/' uzantısı ile listelenen haberler listeye alınıp biri yeni sekme olarak açıldı */
        listeWebElement = m2.getMansetAltıTopluListe();
        List<WebElement> galeriSayfalar = listelemeType1(listeWebElement);
        galeriSayfalar.get(1).sendKeys(newTab);

        /* açılan yeni sekme handle edilip işlem yapılabilir hale getirildi. */
        iterate(driver);


        String currentURL = driver.getCurrentUrl();

       // infinityController(driver);


        System.out.println("--boş newtab öncesi");
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.open('https://www.google.com.tr/', '_blank');");


        windHandler = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windHandler.get(windHandler.size()-1));


        mansetFirstUrl = "site:".concat(mansetFirstUrl);
        System.out.println(mansetFirstUrl);

        driver.findElement(By.xpath("//input[@class = 'gLFyf gsfi']")).sendKeys(mansetFirstUrl, Keys.ENTER);
        driver.findElement(By.xpath("//div[@class='eqAnXb']/div[2]/div/div/div/div/div/div/div[1]")).click();

        infinityController(driver);









    }

    public static List<WebElement> listelemeType1(List<WebElement> listElement){


        List<WebElement> galeriListe = new ArrayList<>();
        String url;

        for(int i=0;i<listElement.size();i++){

            url= listElement.get(i).getAttribute("href");

            if(url.contains("https://www.sabah.com.tr/galeri/")){
                galeriListe.add(listElement.get(i));
                System.out.println(url);
            }


        }

        return galeriListe;
    }


    public static void iterate(WebDriver driver) {

        Set<String> windowHandle = new HashSet<>();
        windowHandle = driver.getWindowHandles();
        Iterator<String> it = windowHandle.iterator();
        it.next();
        driver.switchTo().window(it.next());
    }

    public static boolean infinityController (WebDriver driver) throws InterruptedException {

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

        if(isFound==true){
            System.out.println("BULUNDU ! Utm anasayfa bulunuyor...");
        }
        else{
            System.out.println("BULUNAMADI ! Utm anasayfa bulunamadı...");
        }

        return isFound;
    }

    public static void closeAllTabExcludeLast (WebDriver driver, List<String> windowHandlerList){

        String lastHandle = windowHandlerList.get(windowHandlerList.size()-1);

        //Do something to open new tabs

        for(String handle : windowHandlerList) {
            if (!handle.equals(lastHandle)) {
                driver.switchTo().window(handle);
                driver.close();
            }
        }

        driver.switchTo().window(lastHandle);

    }


}
