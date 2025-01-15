/*package com.browserstack;

import com.browserstack.Config;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BSassignment extends Config {
    @Test(description = "Visit the website El País, a Spanish news outlet Ensure that the website's text is displayed in Spanish.")
    public void First_case() throws Exception 
    {
        String pageText = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue(pageText.contains("Seleccione") || pageText.contains("Actualizado"), "The website text is not in Spanish");
    }

    @Test(description = "Scrape Articles from the Opinion Section:Navigate to the Opinion section of the website.,Fetch the first five articles in this section.,Print the title and content of each article in Spanish.If available, download and save the cover image of each article to your local machine.") 
    public void Second_case() throws Exception {
        driver.findElement(By.xpath("//*[@id=\"csw\"]/div[1]/nav/div/a[2]")).click();
        
    }
}*/
package com.browserstack;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class BSassignment extends Config {
    @Test(description = "Visit the website El País, a Spanish news outlet Ensure that the website's text is displayed in Spanish.")
    public void First_case() throws Exception {

        String pageText = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue(pageText.contains("Seleccione") || pageText.contains("Actualizado"), "The website text is not in Spanish");
    }

    @Test(description = "Scrape Articles from the Opinion Section: Navigate to the Opinion section of the website, fetch the first five articles in this section, print the title and content of each article in Spanish. If available, download and save the cover image of each article to your local machine.")
    public void Second_case() throws Exception {
        // Navigate to the Opinion section
        driver.findElement(By.xpath("//*[@id='csw']/div[1]/nav/div/a[2]")).click();

        // Fetch the first five articles
        List<WebElement> articles = driver.findElements(By.cssSelector("article"));
        for (int i = 0; i < Math.min(articles.size(), 5); i++) {
            WebElement article = articles.get(i);
            String title = article.findElement(By.cssSelector("h2")).getText();
            String content = article.findElement(By.cssSelector("p")).getText();
            System.out.println("Title: " + title);
            System.out.println("Content: " + content);

            // Download and save the cover image if available
            List<WebElement> images = article.findElements(By.tagName("img"));
            if (!images.isEmpty()) {
                String imageUrl = images.get(0).getAttribute("src");
                downloadImage(imageUrl, "article_" + (i + 1) + ".jpg");
            }
        }
    }

    private void downloadImage(String imageUrl, String fileName) throws Exception {
        try (InputStream in = new URL(imageUrl).openStream();
             FileOutputStream out = new FileOutputStream(fileName)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
    }
    @Test(description = "")
    public void Third_case() throws Exception {
        // Navigate to the Opinion section
        driver.findElement(By.xpath("//*[@id='csw']/div[1]/nav/div/a[2]")).click();

        // Fetch the first five articles
        List<WebElement> articles = driver.findElements(By.cssSelector("article"));
        for (int i = 0; i < Math.min(articles.size(), 5); i++) {
            WebElement article = articles.get(i);
            String title = article.findElement(By.cssSelector("h2")).getText();
            String content = article.findElement(By.cssSelector("p")).getText();

            // Translate title and content
            String translatedTitle = translateText(title);
            String translatedContent = translateText(content);

            System.out.println("Original Title: " + title);
            System.out.println("Translated Title: " + translatedTitle);
            System.out.println("Original Content: " + content);
            System.out.println("Translated Content: " + translatedContent);
        }
    }

       
    }


