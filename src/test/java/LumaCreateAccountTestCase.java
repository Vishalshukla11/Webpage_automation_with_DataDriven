import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.example.DataDrivenTEsting.ReadDataFromExcelFile;

public class LumaCreateAccountTestCase {
    WebDriver driver;

    @DataProvider(name = "accountData")
    public Object[][] getAccountData() throws IOException {
        String filePath = "C:\\Users\\acer\\Desktop\\java\\Bridzelabz_softwareTesting_manual_Automation_MobileTesting\\data-driven-testing-using-selenium\\src\\main\\java\\com\\example\\TestData\\DataForDataDrivenTesting.xlsx";
        String sheetName = "Sheet1";
        return ReadDataFromExcelFile.GetExcelData(filePath, sheetName);
    }

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get("https://magento.softwaretestingboard.com/");
        driver.findElement(By.xpath("//div[@class='panel header']//a[normalize-space()='Create an Account']")).click();

    }

   

    @Test(priority = 1)
    public void verifyNewTabTitle() {
        // Verify that the title of the new page is correct
        String title = driver.getTitle();
        Assert.assertEquals(title, "Create New Customer Account");
    }

    @Test(priority = 2)
    public void ClearData() {
        driver.findElement(By.id("firstname")).clear();
        driver.findElement(By.id("lastname")).clear();
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password-confirmation")).clear();

    }

    @Test(dataProvider = "accountData", priority = 3)
    public void testCreateAccount(String firstName, String lastName, String email, String password,
            String confirmPassword) throws InterruptedException {
        // Enter the fetched data into the form fields
        driver.findElement(By.id("firstname")).sendKeys(firstName);
        driver.findElement(By.id("lastname")).sendKeys(lastName);
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("password-confirmation")).sendKeys(confirmPassword);
        Thread.sleep(2000);

        // driver.findElement(By.xpath("//button[@title='Create an Account']")).click();

    }

    @AfterMethod
    public void tearDown() {
        // Close the browser after the test
        driver.quit();
    }
}
