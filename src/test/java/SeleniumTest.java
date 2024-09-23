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
import java.io.File;

public class SeleniumTest {
    private WebDriver webDriver;

    @BeforeEach
     public void setUp() {
        
       System.setProperty("webdriver.chrome.driver", "driver/chromedriver");//linux_64

        // Get file
        File file = new File("src/main/index.html");
        String path = "file://" + file.getAbsolutePath();

        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        webDriver = new ChromeDriver(options);

        // Open the HTML file
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
            webDriver.quit();
    }
}


       
