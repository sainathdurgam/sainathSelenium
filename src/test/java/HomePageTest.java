import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;
import pages.*;

public class HomePageTest {
    public WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;

    @BeforeMethod
    public void setUp(){

        System.setProperty("webdriver.chrome.driver","C:\\Users\\HP\\Downloads\\chromedriver-win32 (2)\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://qamoviesapp.ccbp.tech");
        loginPage = new LoginPage(driver);
        homePage= new HomePage(driver);

        loginPage.loginToApplication("rahul", "rahul@2021");

        String expectedUrl = "https://qamoviesapp.ccbp.tech/";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));

        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, expectedUrl, "URLs do not match");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void homepage(){

        //Assert.assertEquals(actualCount,3,"header count dsnt match");
        Assert.assertTrue(homePage.play().isDisplayed(),"play btn dsnt display");
        Assert.assertTrue(homePage.footer().isDisplayed(),"footer dnt displayed");
        Assert.assertEquals(homePage.headCount(),3,"count dst match");
        Assert.assertEquals(homePage.movesInt(),20,"movies count dsnt matched");

    }

    @Test(priority = 1)
    public void headersectionUi(){
        Assert.assertTrue(homePage.web().isDisplayed(),"logo dsnt display inside");
        Assert.assertTrue(homePage.home().isDisplayed(),"home anchor dsnt");
        Assert.assertTrue(homePage.popular().isDisplayed(),"popular dsnt display");
        Assert.assertTrue(homePage.profile().isDisplayed(),"profile dsnt dislpay");
    }

    @Test(priority = 2)
    public void headerSectionFunctionalities(){
        homePage.home().click();
        homePage.popular().click();
        homePage.profile().click();
    }





}
