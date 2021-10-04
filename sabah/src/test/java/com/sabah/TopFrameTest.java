package com.sabah;

import Assertion.Assert;
import model.urlType1;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.anasayfa.Manset;
import pageObjects.anasayfa.MansetAltı;
import pageObjects.anasayfa.Surmanset;
import pageObjects.anasayfa.TopFrame;
import resources.base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TopFrameTest extends base {

    public WebDriver driver;
    public static Logger log =LogManager.getLogger(base.class.getName());
    SoftAssert softAssert = new SoftAssert();
    Assert a1 = new Assert();
    TopFrame t1;
    Surmanset s1;
    Manset m1;
    MansetAltı m2;
    List<WebElement> listeWebElement;

    @BeforeTest
    public void initialize() throws IOException
    {
        driver =initializeDriver();
        driver.get(prop.getProperty("url"));
        t1=new TopFrame(driver);
        s1=new Surmanset(driver);
        m1=new Manset(driver);
        m2=new MansetAltı(driver);

    }

    @Test(description = "Top menu kontrol")
    public void TopMenuControl() throws IOException {

        /* Top menude yer alan spor,günaydın,roza,memurlar vb. markaların url kontrolleri */
        listeWebElement = t1.getNavTopMenuList();
        a1.CheckValidation(listelemeType1(listeWebElement),softAssert);
        softAssert.assertAll();

    }

    @Test(description = "Topmenu broken image kontrolleri")
    public void TopMenuBrokenImageControl() throws IOException{

        /* Markaların broken image kontrolleri */
        listeWebElement= t1.getNavTopMenuList();
        a1.CheckValidationImage(ImageListeleme(listeWebElement),softAssert);
        softAssert.assertAll();

    }

    @Test(description = "Navbar menu kontrol")
    public void TopMenuNavBarControl() throws IOException {

        /* Top menude yer alan sondakika,gündem,ekonomi,yaşam,sağlık vb. sayfaların url kontrolleri */

        listeWebElement = t1.getNavobileDesktopMenuList();
        a1.CheckValidation(listelemeType1(listeWebElement),softAssert);
        //a1.CheckValidationImage(listelemeImage(listeWebElement));
        softAssert.assertAll();

    }

    //@Test
    public void CurrencyBarControl(){
        /*currency bar control*/
    }

    @Test(description = "surmanset 5'li kontrol")
    public void SurmansetControl() throws IOException{

        /* Surmanset kontrol #5'li slider */

        listeWebElement = s1.getSurmansetList();
        a1.CheckValidation(listelemeType1(listeWebElement),softAssert);
        softAssert.assertAll();

    }

    @Test
    public void SurmansetAltıControl() throws IOException{

        /*surmanset altı 4'lü haber kontrol*/

        listeWebElement= s1.getSurmansetAltı4lu();
        a1.CheckValidation(listelemeType1(listeWebElement),softAssert);
        softAssert.assertAll();


    }

    @Test
    public void MansetControl() throws IOException{
        /*Manset 20'li kontrol*/
        listeWebElement= m1.getManset20liList();
        a1.CheckValidation(listelemeType1(listeWebElement),softAssert);
        softAssert.assertAll();
    }


    //@Test
    public void MansetYanı4luControl() throws IOException{

        /*Manset yanı 4lü haber kontrol */

        listeWebElement= m1.getMansetyanı4luList();
        a1.CheckValidation(listelemeType1(listeWebElement),softAssert);
        softAssert.assertAll();
    }

    //@Test
    public void MansetAltı5liControl() throws IOException{

        /*Manset altı 5'li haber kontrol */

        listeWebElement=m1.getMansetAlti5liList();
        a1.CheckValidation(listelemeType1(listeWebElement),softAssert);
        softAssert.assertAll();

    }

    @Test
    public void MansetAltıTopluControl() throws IOException{

        /*Manset altı toplu haber kontrol #75haber */

        listeWebElement=m2.getMansetAltıTopluListe();
        a1.CheckValidation(listelemeType1(listeWebElement),softAssert);
        softAssert.assertAll();

    }




    @AfterTest
    public void teardown() {
        driver.close();
    }

    private List<urlType1> listelemeType1(List<WebElement> listElement){

        urlType1 objectUrl;
        List<urlType1> elementListType1 = new ArrayList<>();
        String url;
        String elementName;

        for(int i=0;i<listElement.size();i++){

            url= listElement.get(i).getAttribute("href");
            elementName=listElement.get(i).getAttribute("innerHTML");

            if(elementName.contains("<img src")){
                elementName=listElement.get(i).findElement(By.xpath(".//img")).getAttribute("alt");}
            //else if(elementName.contains())
            objectUrl = new urlType1(url,elementName);
            elementListType1.add(objectUrl);
            //System.out.println(elementName);
        }

        return elementListType1;
    }


    private List<String> ImageListeleme (List<WebElement> listElement){

        List<String> ImageUrlList = new ArrayList<>();
        String url;

        for (int i=0;i<listElement.size();i++){


               url = listElement.get(i).findElement(By.xpath(".//img")).getAttribute("src");
               ImageUrlList.add(url);
               //System.out.println(url);

        }

        return ImageUrlList;
    }




}
