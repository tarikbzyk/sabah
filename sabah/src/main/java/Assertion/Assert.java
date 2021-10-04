package Assertion;

import HttpControl.urlControl;
import model.urlType1;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.List;

public class Assert {

    public void CheckValidation(List<urlType1> liste , SoftAssert softAssert) throws IOException {


        urlControl u1 = new urlControl();
        String contentUrl;
        int contentUrlResponse;
        int sayac=0;


            for (int i = 0; i < liste.size(); i++) {
                contentUrl = liste.get(i).getContentUrl();
                contentUrlResponse = u1.responseCode(contentUrl);
                softAssert.assertNotNull(contentUrl);

                System.out.println("[ "+contentUrl+" ]"+" için response kodu : "+"["+contentUrlResponse+"]");

                if (contentUrlResponse != 200 && contentUrlResponse != 301 && contentUrlResponse != 502 && contentUrlResponse != 403) {
                    softAssert.fail("[" + contentUrlResponse + "]" + " is not expected..." + "For this url : " + contentUrl);
                    sayac++;
                }
            }

            if(sayac==0){
                System.out.println("-------------------"+"\n"+"Check validation'da "+liste.size()+" kontrol yapıldı. Hatasız tamamlandı !"+"\n"+"-------------------");}
            else if (sayac>0){
                System.out.println("-------------------"+"\n"+"Check validation'da "+liste.size()+" kontrol yapıldı. "+sayac+" hata ile tamamlandı. KONTROL EDİNİZ!!!"+"\n"+"-------------------");
            }




    }

    public void CheckValidationImage(List<String> liste , SoftAssert softAssert) throws IOException{
        urlControl u1 = new urlControl();

        String contentUrl;
        int contentUrlResponse;
        int sayac=0;

        for (int i=0;i<liste.size();i++){
            contentUrl = liste.get(i);
            contentUrlResponse = u1.responseCode(contentUrl);
            softAssert.assertNotNull(contentUrl);

            if(contentUrlResponse != 200 && contentUrlResponse != 301 && contentUrlResponse != 502 && contentUrlResponse != 403){
                softAssert.fail("["+contentUrlResponse+"]"+ " is not expected..."+"For this url : "+contentUrl);
                sayac++;
            }
        }


        if(sayac==0){
            System.out.println("-------------------"+"\n"+"Image Check validation'da "+liste.size()+" kontrol yapıldı. Hatasız tamamlandı !"+"\n"+"-------------------");}
        else if (sayac>0){
            System.out.println("-------------------"+"\n"+"Image Check validation'da "+liste.size()+" kontrol yapıldı. "+sayac+" hata ile tamamlandı. KONTROL EDİNİZ!!!"+"\n"+"-------------------");
        }


    }
}
