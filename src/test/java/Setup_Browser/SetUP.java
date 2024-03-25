package Setup_Browser;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeTest;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class SetUP {
    public static WebDriver driver;

    @BeforeTest
    public void setupBrowser() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins");
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");


        try {
            InputStream input = new FileInputStream("config.yaml");
            Yaml yaml = new Yaml();
// Inside the try block where you load the YAML file
            Map<String, Object> data = yaml.load(input);
            List<String> urls = (List<String>) data.get("urls");

            input.close();


            if (urls != null && !urls.isEmpty()) {
                for (String url : urls) {
                    try {
                        driver = new ChromeDriver(options);
                        driver.get(url);
                        driver.manage().window().maximize();
                        // Perform checks on the webpage (e.g., verify title, elements, etc.)
                        // If everything is as expected, proceed with the test
                        // Example:
                        if (!driver.getTitle().isEmpty()) {
                            System.out.println("Successfully navigated to: " + url);
                            // Proceed with the test
                        } else {
                            throw new IOException("Failed to load page: " + url);
                        }
                    } catch (TimeoutException e) {
                        System.err.println("Timeout while loading page: " + url);
                        e.printStackTrace();
                    } catch (Exception e) {
                        System.err.println("Error occurred while navigating to: " + url);
                        e.printStackTrace();
                    } finally {

                    }
                }
            } else {
                throw new IOException("URLs not found or empty in the YAML file.");
            }
        } catch (IOException e) {
            System.err.println("Error loading YAML file.");
            e.printStackTrace();
        }
    }
}

