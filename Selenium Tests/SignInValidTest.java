// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class SignInValidTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void signInValid() {
    driver.get("https://www.homedepot.ca/myaccount");
    driver.manage().window().setSize(new Dimension(1373, 1339));
    {
      WebElement element = driver.findElement(By.cssSelector(".hdca-button-container:nth-child(7) .acl-button__label"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    driver.findElement(By.cssSelector("html")).click();
    driver.findElement(By.cssSelector(".acl-col--12:nth-child(1) > div > .acl-mt--medium")).click();
    driver.findElement(By.cssSelector(".lg\\3A acl-col--5:nth-child(1)")).click();
    assertThat(driver.findElement(By.cssSelector(".acl-col--12:nth-child(1) > div > .acl-mt--medium")).getText(), is("Sign In"));
    driver.findElement(By.id("login.emailAddress")).click();
    driver.findElement(By.id("login.emailAddress")).sendKeys("sj1212@gmail.com");
    driver.findElement(By.id("login.password")).click();
    driver.findElement(By.id("login.password")).sendKeys("software69420");
    driver.findElement(By.cssSelector(".acl-button--theme--primary:nth-child(1)")).click();
    {
      WebElement element = driver.findElement(By.linkText("Add Addresses"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    {
      WebElement element = driver.findElement(By.tagName("body"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element, 0, 0).perform();
    }
    driver.findElement(By.cssSelector("#profile-information .acl-card__content")).click();
    assertThat(driver.findElement(By.cssSelector("#profile-information .acl-title--large")).getText(), is("Profile Information"));
    driver.findElement(By.cssSelector(".acl-ml--x-small > svg")).click();
    driver.findElement(By.cssSelector(".acl-display--hide > .acl-button")).click();
  }
}