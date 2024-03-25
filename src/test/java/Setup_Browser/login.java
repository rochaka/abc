package Setup_Browser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import static Setup_Browser.SetUP.driver;

public class login {
    @Test
    public void loging() throws InterruptedException {
        Thread.sleep(5000);
        // Path to your YAML config file
        String yamlFilePath = "config.yaml";

        // Load data from the YAML config file
        Map<String, String> configData;
        try (InputStream input = new FileInputStream(yamlFilePath)) {
            Yaml yaml = new Yaml();
            configData = yaml.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Get username and password from the loaded data
        String username = configData.get("email");
        String password = configData.get("password");

        // Perform login using the retrieved username and password
        //WebDriverWait wait = new WebDriverWait(driver,10):
        Thread.sleep(1000);

        driver.findElement(By.id("email")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email"))).sendKeys(username);
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password"))).sendKeys(password);
        Thread.sleep(5000);
        WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/form/button"));
        submitButton.click();

        //WebElement submitButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"app\"]/div/div[2]/form/button")));
        //submitButton.click();
    }
}

