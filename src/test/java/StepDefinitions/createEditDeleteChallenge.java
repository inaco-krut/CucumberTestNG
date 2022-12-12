package StepDefinitions;

import io.cucumber.java.en.And;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.Duration;
import java.util.List;

import static org.testng.Assert.*;

public class createEditDeleteChallenge {
    static WebDriver driver;
    static WebDriverWait wait;
    String ChallengeTitle = "ThisIsATestTitle";
    String ChallangeDesc = "ThisIsATestDesc";
    String ChallangeImage = "https://styles.redditmedia.com/t5_2r5i1/styles/communityIcon_x4lqmqzu1hi81.jpg";
    @Given("The admin is on the landing page")
    public void The_admin_is_on_the_landing_page() throws Exception {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://nexerboostappadmin.azurewebsites.net/home");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        wait = new WebDriverWait(driver, Duration.ofSeconds(30), Duration.ofSeconds(2));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("i0116")));
        addCookies.addAllCookies(driver);
        driver.navigate().refresh();
    }
    @Given("The admin press challenge button")
    public void the_admin_press_challenge_button() {
        System.out.println("Press challenge button");
        driver.findElement(By.xpath("/html/body/app-root/app-main-nav/mat-sidenav-container/mat-sidenav-content/mat-toolbar/mat-list/a[3]")).click();
        String actualURL = driver.getCurrentUrl();
        assertEquals(actualURL, "https://nexerboostappadmin.azurewebsites.net/challenge");
        System.out.println("Test passed - URL Confirmed\n");
    }
    @Given("The admin press create new challenge button")
    public void the_admin_press_create_new_challenge_button() {
        System.out.println("Press 'create new challenge' button");
        driver.findElement(By.xpath("//span[contains(@class, 'mat-button-wrapper') and text()=' Create New Challenge ']")).click();
        assertTrue(driver.getPageSource().contains("CREATE NEW CHALLENGE"));
        System.out.println("Test passed - POP-UP showing\n");
    }
    @Given("The admin fills out challenge information")
    public void the_admin_fills_out_challenge_information() {
        System.out.println("Fill out challenge title, description and image url");

        driver.findElement(By.id("mat-input-0")).sendKeys(ChallengeTitle);
        String titleValue = driver.findElement(By.id("mat-input-0")).getAttribute("value");
        assert titleValue.equals("ThisIsATestTitle");

        driver.findElement(By.id("mat-input-1")).sendKeys(ChallangeDesc);
        String descValue = driver.findElement(By.id("mat-input-1")).getAttribute("value");
        assert descValue.equals("ThisIsATestDesc");

        driver.findElement(By.id("mat-input-2")).sendKeys(ChallangeImage);
        String imageValue = driver.findElement(By.id("mat-input-2")).getAttribute("value");
        assert imageValue.equals("https://styles.redditmedia.com/t5_2r5i1/styles/communityIcon_x4lqmqzu1hi81.jpg");

        System.out.println("Test passed - Challenge information injected\n");

    }

    @When("Admin then press Save button")
    public void admin_then_press_save_button() throws Exception {
        System.out.println("Press 'Save' button");
        driver.findElement(By.xpath("//span[contains(@class, 'mat-button-wrapper') and text()=' Save ']")).click();
        Thread.sleep(2000);
        assertTrue(driver.getPageSource().contains("Challenge has been saved successfully!"));
        System.out.println("Test passed - Challenge successfully saved\n");
    }

    @And("Admin edit the challenge")
    public void admin_Edit_The_Challenge() throws Exception {
        System.out.println("Edit the challenge\n");
        driver.findElement(By.xpath("//span[contains(@class, 'mat-button-wrapper') and text()='Close']")).click();
        Thread.sleep(2000);
        driver.navigate().refresh();
        String xpath = ("//*[contains(text(),'EDIT')]");
        List<WebElement> elementList = driver.findElements(By.xpath(xpath));
        elementList.get(elementList.size()-1).click();
        Thread.sleep(2000);
        driver.findElement(By.id("title")).sendKeys("ThisIsATestTitleUpdated");
        driver.findElement(By.id("description")).sendKeys("ThisIsATestDescUpdated");
        driver.findElement(By.id("imageurl")).sendKeys("https://external-preview.redd.it/YC7u8USzLcS-nnH8yCBgc1u_oI0NG7rhiyxvt23hvWk.jpg?auto=webp&s=3117520819a38630a32d088f31c2fc7e3f10b940");
        driver.findElement(By.xpath("//span[contains(@class, 'mat-button-wrapper') and text()='Save']")).click();
        driver.findElement(By.xpath("//span[contains(@class, 'mat-button-wrapper') and text()=' Yes']")).click();
        driver.findElement(By.xpath("//span[contains(@class, 'mat-button-wrapper') and text()=' OK']")).click();
        //insert assert here
    }

    @Then("Admin delete the challenge")
    public void challenge_Should_Be_Deleted() {
        System.out.println("Delete the challenge");
        String xpath = ("//*[contains(text(),'EDIT')]");
        List<WebElement> elementList = driver.findElements(By.xpath(xpath));
        elementList.get(elementList.size()-1).click();
        assertTrue(driver.getPageSource().contains("ThisIsATestDesc"));
        driver.findElement(By.xpath("//span[contains(@class, 'mat-button-wrapper') and text()='Delete']")).click();
        driver.findElement(By.xpath("//span[contains(@class, 'mat-button-wrapper') and text()=' Yes']")).click();
        driver.findElement(By.xpath("//span[contains(@class, 'mat-button-wrapper') and text()=' OK']")).click();
        driver.navigate().refresh();
        assertFalse(driver.getPageSource().contains("ThisIsATestDesc"));
        System.out.println("Test passed - Challenge successfully deleted\n");
        driver.quit();

    }
}
