package com.sabah;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import resources.base;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App extends base
{


    public static void main( String[] args ) throws IOException, InterruptedException {
        base bas = new base();
        WebDriver driver = bas.initializeDriver();
        driver.get("https://www.sabah.com.tr/spor/futbol/2021/09/13/vitor-pereiradan-altay-bayindira-destek-sen-bize-cok-mac-kazandirdin-takim-halinde-puan-kaybettik");

        WebElement most_reading_widget = driver.findElement(By.xpath("//div[@class='most-reading-widget']"));
        //Thread.sleep(5000L);


        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView(true);", most_reading_widget);
        WebElement multi_infinity = driver.findElement(By.xpath("//div[@class='section infinite selectionShareable'][1]"));
        jse.executeScript("arguments[0].scrollIntoView(true);", multi_infinity);
        //jse.executeScript(most_reading_widget,"" );



    }


}
