import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import java.io.File;

public class RelationalSelectorsTest {
    private WebDriver webDriver;

    @BeforeEach
    public void setUp() {
        String browserName = BrowserUtils.getBrowserName();

        switch (browserName) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("headless");
                webDriver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("-headless");
                webDriver = new FirefoxDriver(firefoxOptions);
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--headless");
                webDriver = new EdgeDriver(edgeOptions);
                break;

            case "ie":
                WebDriverManager.iedriver().setup();
                InternetExplorerOptions ieOptions = new InternetExplorerOptions();
                ieOptions.addCommandSwitches("-headless");
                webDriver = new InternetExplorerDriver(ieOptions);
                break;

            case "chromium":
            WebDriverManager.chromiumdriver().setup();
            ChromeOptions chromiumOptions = new ChromeOptions();
            chromiumOptions.addArguments("headless");
            webDriver = new ChromeDriver(chromiumOptions);
            break;

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }
        File file = new File("src/main/java/com/revature/index.html");
        String path = "file://" + file.getAbsolutePath();
        webDriver.get(path);
    }
    @Test
    public void testRelationalSelectors() {
        try {

 // Test Child elements background color
 WebElement childElement1 = webDriver.findElement(By.cssSelector("#parent > #child1"));
 String childElement1BgColor = childElement1.getCssValue("background-color");
 Assertions.assertEquals("rgba(173, 216, 230, 1)", childElement1BgColor); 

 WebElement childElement2 = webDriver.findElement(By.cssSelector("#parent > #child2"));
 String childElement2BgColor = childElement2.getCssValue("background-color");
 Assertions.assertEquals("rgba(173, 216, 230, 1)", childElement2BgColor); 

 // Test h2 elements font size and color
 WebElement h2Element1 = webDriver.findElement(By.cssSelector("#parent > #child1 > h2"));
 String h2Element1FontSize = h2Element1.getCssValue("font-size");
 Assertions.assertEquals("20px", h2Element1FontSize); 

 WebElement h2Element2 = webDriver.findElement(By.cssSelector("#parent > #child2 > h2"));
 String h2Element2Color = h2Element2.getCssValue("color");
 Assertions.assertEquals("rgba(0, 128, 0, 1)", h2Element2Color); 

 // Test #sibling element border and background color
 WebElement siblingElement = webDriver.findElement(By.cssSelector("#sibling"));
 String siblingElementBorder = siblingElement.getCssValue("border");
 Assertions.assertEquals("1px solid rgb(0, 0, 0)", siblingElementBorder); 

 String siblingElementBgColor = siblingElement.getCssValue("background-color");
 Assertions.assertEquals("rgba(255, 255, 0, 1)", siblingElementBgColor); 

 // Test General sibling elements background color and margin
 WebElement generalSibling1 = webDriver.findElement(By.cssSelector("#general-sibling"));
 String generalSibling1BgColor = generalSibling1.getCssValue("background-color");
 Assertions.assertEquals("rgba(211, 211, 211, 1)", generalSibling1BgColor);

 WebElement generalSibling2 = webDriver.findElement(By.cssSelector("#general-sibling2"));
 String generalSibling2MarginTop = generalSibling2.getCssValue("margin-top");
 Assertions.assertEquals("10px", generalSibling2MarginTop);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}

class BrowserUtils {
    public static String getBrowserName() {
        WebDriver driver = null;
        try {
            // Attempt to set up WebDriverManager for Chrome
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            return "chrome";
        } catch (Exception eChrome) {
            // If Chrome setup fails, try setting up for Firefox
            try {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                return "firefox";
            } catch (Exception eFirefox) {
                // If Firefox setup fails, try setting up for Edge
                try {
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    return "edge";
                } catch (Exception eEdge) {
                    // If Edge setup fails, try setting up for Internet Explorer
                    try {
                        WebDriverManager.iedriver().setup();
                        driver = new InternetExplorerDriver();
                        return "ie";
                    } catch (Exception E) {
                        // If none of the above work, you can handle it accordingly
                        throw new RuntimeException("No supported browser  or the driver is Installed! ");
                    }
                }
            }
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
    
}