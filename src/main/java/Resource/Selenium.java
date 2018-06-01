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
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

import java.security.Key;
import java.util.List;
import java.util.Scanner;

public class Selenium extends Steps{


    public static WebDriver driver = null;

    //Scenario: Login Home
    @Given("Open browser")
    public void Open(){

        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "D:\\Nghia\\SeleniumNewCC\\Chrome\\chromedriver.exe");
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

        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement user = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/form/div/div[1]/input")));
        user.sendKeys("sony");
        WebElement pw = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/form/div/div[2]/input")));
        pw.sendKeys("Qup@12345");
        WebElement login = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/form/div/button")));
        login.click();
    }


    //Scenario: Login Driver
    @Given("Open form Add Driver")
    public void openDrivermoduel(){

        try {
            Thread.sleep(1800);
            driver.get("https://cclite.lab.qup.vn/driver");
        }catch (InterruptedException e){
            System.out.println("Failed");
        }
    }
    @When ("Open Driver module")
    public void clickAdd(){

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"page-content\"]/div/div/div[1]/button[1]")));
        add.click();
    }

    //Scenario: input data
    @Then("Input data")
    public void checkView(){

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement phone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"page-content\"]/div/div/div/div[2]/div[3]/div[2]/div/div/input")));
        phone.sendKeys("2055550090");
        WebElement first = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"page-content\"]/div/div/div/div[2]/div[3]/div[3]/input")));
        first.sendKeys("Driver");
        WebElement last = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"page-content\"]/div/div/div/div[2]/div[3]/div[4]/input")));
        last.sendKeys("Test");
        Select comp = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"page-content\"]/div/div/div/div[1]/div[2]/select"))));
        comp.selectByIndex(2);
        WebElement vehicel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"react-select-2--value\"]")));
        vehicel.click();
        //driver.findElement(By.cssSelector("Body")).sendKeys(Keys.DOWN);
        Actions action = new Actions(driver);
        action.keyUp(Keys.CONTROL).sendKeys(Keys.ENTER).build().perform();
        Select zone = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"formControlsSelectMultiple\"]"))));
        zone.selectByIndex(1);
        Select shif = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"formControlsSelectMultiple\"]"))));
        shif.selectByIndex(1);
        WebElement active = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("//*[@id=\"page-content\"]/div/div/div/div[1]/div[20]")));
        active.click();

    }
}


    /*
    //Scenario: New Booking
    @When("Open form Newbooking")
    public void formNewbooking (){

        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement newBooking = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("menu-addnew")));
        newBooking.click();
        try {
            Thread.sleep(1800);
            driver.findElement(By.className("menu-addnew")).click();
        }catch(InterruptedException e){
            System.out.println(e);
        }
    }
    @Then("Input data")
    public void inputDate() {

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"page-content\"]/span/div/div[1]/div[2]/div[2]/div/input")));
        element.sendKeys("san bay");
         // Create an interface WebElement of the div
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
        WebElement des = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"page-content\"]/span/div/div[1]/div[3]/div[2]/div/input")));
        des.sendKeys("hoang dieu");
        WebElement dest = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"PlacesAutocomplete__autocomplete-container\"]/div[1]")));
        dest.click();

        WebElement phone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"page-content\"]/span/div/div[1]/div[9]/div/input")));
        des.sendKeys("2055550010");
        //WebElement create = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"page-content\"]/span/div/div[2]/div[7]/button[1]")));
        //dest.click();
    }*/