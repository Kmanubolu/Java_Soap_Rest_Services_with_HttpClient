package runner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SoapServicewithhttpclient {
	
	@Parameters({"XMLFilePath"})
	@Test
	public  void test(String strXMLFilename) throws ClientProtocolException, IOException, InterruptedException{
	//public static void main(String[] args) throws IOException, InterruptedException {

		String strURL = "http://www.webservicex.com/globalweather.asmx";
		//String strXMLFilename = "C:\\Selenium\\WorkSpace\\Java_Soap_Rest_Services_with_HttpClient\\request1.xml";
	    File input = new File(strXMLFilename);
        HttpPost post = new HttpPost(strURL);
        post.setEntity(new InputStreamEntity(new FileInputStream(input), input.length()));
        post.setHeader("Content-type", "text/xml; charset=ISO-8859-1");
        HttpClient httpclient = new DefaultHttpClient();
        // Execute request
        try {
            HttpResponse response = httpclient.execute(post);
            // Display status code
            System.out.println("Response status code: " + response);
            // Display response
            System.out.println("Response body: ");
            System.out.println(response.toString());
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
            	result.append(line);
            	System.out.println(line.toString());
            }
            System.out.println(result.toString());
        } finally {
            // Release current connection to the connection pool 
            // once you are done
            post.releaseConnection();
        }
        Thread.sleep(5000);
	}
}
