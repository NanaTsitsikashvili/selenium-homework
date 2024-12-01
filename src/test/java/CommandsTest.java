import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CommandsTest {
private WebDriver driver;

    @BeforeMethod
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, java.util.concurrent.TimeUnit.SECONDS);
    }

    @Test
    public void testDynamicControlsAndDragAndDrop() {

        driver.get("http://the-internet.herokuapp.com/dynamic_controls");
        WebElement enableButton = driver.findElement(By.xpath("//button[contains(text(), 'Enable')]"));
        enableButton.click();
        WebElement inputField = driver.findElement(By.xpath("//input[@type='text']"));
        WebElement message = driver.findElement(By.id("message"));
        Assert.assertTrue(inputField.isEnabled(), "Input field should be enabled.");
        Assert.assertEquals(message.getText(), "It's enabled!", "Incorrect enable message.");
        Assert.assertEquals(enableButton.getText(), "Disable", "Button text did not change to Disable.");
        inputField.sendKeys("Bootcamp");
        inputField.clear();
       driver.get("http://the-internet.herokuapp.com/drag_and_drop");


        WebElement columnA = driver.findElement(By.id("column-a"));
        WebElement columnB = driver.findElement(By.id("column-b"));

          int columnAY = columnA.getLocation().getY();
        int columnBY = columnB.getLocation().getY();

        Assert.assertEquals(columnAY, columnBY, "The Y coordinates of columns A and B are different.");
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
