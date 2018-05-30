package Resource;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.ScenarioType;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.steps.BeforeOrAfterStep;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.StepCandidate;
import org.jbehave.core.steps.Steps;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Selenium extends Steps{
    public static WebDriver driver = null;

    @Given("Open brower")
    public void Open(){
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "D:\\Nghia\\NewCC\\Chrome\\chromedriver.exe");
            DesiredCapabilities a = DesiredCapabilities.chrome();
            a.setCapability("marionette", true);
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-extensions");
            driver = new ChromeDriver(options);
            driver.get("https://cclite.lab.qup.vn/");
            //driver.manage().window().maximize()
        }
        driver.manage().deleteAllCookies();

    }
    @When("Navigation website QUp")
    public void Login(){

        try {
            Thread.sleep(1800);
            driver.findElement(By.xpath("//*[@id=\"root\"]/div/form/div/div[1]/input")).sendKeys("sony");
            driver.findElement(By.xpath("//*[@id=\"root\"]/div/form/div/div[2]/input")).sendKeys("Qup@12345");
            Thread.sleep(1800);
            driver.findElement(By.xpath("//*[@id=\"root\"]/div/form/div/button")).click();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
    @Then("QUp New CC Map display")
    public void Action(String titlel) {

        org.junit.Assert.assertEquals(titlel, driver.getTitle());
    }
    //Scenario: New Booking
    @When("Open form Newbooking")
    public void formNewbooking (){

        try {
            Thread.sleep(1800);
            driver.findElement(By.className("menu-addnew")).click();
        }catch(InterruptedException e){
            System.out.println(e);
        }
    }
    @Then("Input data")
    public void inputDate(){

        WebDriverWait wait = new WebDriverWait(driver, 3);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"page-content\"]/span/div/div[1]/div[2]/div[2]/div/input")));
        element.sendKeys("san bay");
        // // Create an interface WebElement of the div
        WebElement webElement = driver.findElement(By.id("PlacesAutocomplete__autocomplete-container"));
        // Create an IList and intialize it with all the elements of div under div
        List<WebElement> AllCheckBoxes = webElement.findElements(By.xpath("//*[@id=\"PlacesAutocomplete__autocomplete-container\"]/div"));
        int RowCount = AllCheckBoxes.size();
        // List text with key "san bay"
        for(WebElement ele: AllCheckBoxes) {
            String name = ele.getText();
            System.out.println(" List: "+ name);
        }
        // Select airport locaton 1st
        WebElement air = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"PlacesAutocomplete__autocomplete-container\"]/div[1]")));
        air.click();
    }
}
