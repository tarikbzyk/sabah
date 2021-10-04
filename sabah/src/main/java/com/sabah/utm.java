package com.sabah;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import pageObjects.anasayfa.MansetAltı;
import pageObjects.haberDetay.leftModal;
import resources.base;

import java.io.IOException;
import java.util.*;

public class utm extends base {



    public static void main(String[] args) throws IOException, InterruptedException {

        base con = new base();
        WebDriver driver = con.initializeDriver();
        driver.get("https://www.sabah.com.tr");
        List<WebElement> listeWebElement = new ArrayList<>();
        String newTab = Keys.chord(Keys.CONTROL, Keys.ENTER);
        Set<String> windowHandle = new HashSet<>();

        /* gerekli xpathları çekmek için bulundukları sınıflardan nesneler tanımlandı */
        leftModal l1 = new leftModal(driver);
        MansetAltı m2 = new MansetAltı(driver);


        /* anasayfada '/galeri/' uzantısı ile listelenen haberler listeye alınıp biri yeni sekme olarak açıldı */
        listeWebElement = m2.getMansetAltıTopluListe();
        List<WebElement> galeriSayfalar = listelemeType1(listeWebElement);
        galeriSayfalar.get(1).sendKeys(newTab);

        /* açılan yeni sekme handle edilip işlem yapılabilir hale getirildi. */
        windowHandle = driver.getWindowHandles();
        iterate(driver,windowHandle);


        String currentURL = driver.getCurrentUrl();

        infinityController(driver);

        if(infinityController(driver)){
            System.out.println("BULUNDU ! Utm anasayfa bulunuyor...");
        }
        else if (!(infinityController(driver))){
            System.out.println("BULUNAMADI ! Utm anasayfa bulunamadı...");
        }


        driver.quit();



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


    public static void iterate(WebDriver driver, Set<String> windowHandle) {

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
            System.out.println(currentURL1);

            if (listeUrl.size()>8 && listeUrl.get(listeUrl.size()-1).equals(listeUrl.get(listeUrl.size()-8))){
                System.out.println("Anasayfa bulunamadı");
                isFound=false;
                break;
            }

        }

        return isFound;
    }


}
