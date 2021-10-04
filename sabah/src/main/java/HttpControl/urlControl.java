package HttpControl;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.List;

public class urlControl {




    public int responseCode(String url) throws IOException {
        HttpResponse response = null;
        try {

            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(url);
            response = client.execute(request);



        } catch (IllegalArgumentException e) {
            return 0;
        }
        catch (ClientProtocolException e){
            return 301;
        }

        return response.getStatusLine().getStatusCode();
    }





}


