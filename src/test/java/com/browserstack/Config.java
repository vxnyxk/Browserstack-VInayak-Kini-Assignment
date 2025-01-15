package com.browserstack;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

//import browserstack.shaded.okhttp3.OkHttpClient;
//import browserstack.shaded.okhttp3.RequestBody;
//import browserstack.shaded.okhttp3.Request;
//import browserstack.shaded.okhttp3.Response;
//import browserstack.shaded.okhttp3.MediaType;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import org.json.JSONObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Config {
    public WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    //@SuppressWarnings("unchecked")
    public void setUp() throws Exception {
        // Set the path to the ChromeDriver executable
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://elpais.com/");
        driver.findElement(By.xpath("//*[@id=\"didomi-notice-agree-button\"]")).click();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
    }

   @SuppressWarnings("null")
public String translateText(String text) throws Exception {
    OkHttpClient client = new OkHttpClient();

    MediaType mediaType = MediaType.parse("application/json");
    RequestBody body = RequestBody.create(mediaType, "{\"from\":\"en\",\"to\":\"ar\",\"q\":\"Hello ! Rapid Translate Multi Traduction\"}");
    Request request = new Request.Builder()
	    .url("https://rapid-translate-multi-traduction.p.rapidapi.com/t")
	    .post(body)
	    .addHeader("x-rapidapi-key", "5245ca962cmshe0eb27367b4f6ddp13729ajsn14cef7031a66")
	    .addHeader("x-rapidapi-host", "rapid-translate-multi-traduction.p.rapidapi.com")
	    .addHeader("Content-Type", "application/json")
	    .build();

    Response response = client.newCall(request).execute();
    //@SuppressWarnings("null")
    String responseBody = response.body().string();
    return  responseBody;
    //System.out.println("API Response: " + responseBody); // Log the response body
    // Assuming the response is in JSON format and contains a field "translatedText"
    //JSONObject jsonObject = new JSONObject(responseBody);
    //return jsonObject.getString("translatedText");
}
}