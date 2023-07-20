package MailstepDefs;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyStepdefs {

    private WebDriver driver;


    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);

        driver.get("https://login.mailchimp.com/signup/");
        driver.manage().window().maximize();

        driver.findElement(By.cssSelector("#onetrust-accept-btn-handler")).click();


    }

    @Given("I have email {string}")
    public void iHaveEmailEmail(String email) {
        WebElement mail = driver.findElement(By.id("email"));
        mail.sendKeys(email);


    }

    @Given("I have random username {int}")
    public void iHaveUsernameUsername(int username) throws InterruptedException {
        WebElement myUser = driver.findElement(By.id("new_username"));
        myUser.click();
        myUser.clear();

        if (username == 3) {
            String taken = "hubbahubbabo";
            myUser.sendKeys(taken);

        } else {
            StringBuilder user = new StringBuilder();
            String letters = "abcdefghijklmnopqrstuvwxyz";
            for (int i = 0; i < username; i++) {
                user.append(letters.charAt((int) (Math.random() * letters.length())));
            }
            myUser.sendKeys(user.toString());
        }
        Thread.sleep(2000);
    }

    @Given("I have password {string}")
    public void iHavePasswordPassword(String password) throws InterruptedException {
        driver.findElement(By.id("new_password")).sendKeys(password);
        Thread.sleep(2000);
    }

    @When("I click on sign up")
    public void iClickOnSignUp() throws InterruptedException {
        WebElement click = driver.findElement(By.id("create-account-enabled"));
        click.click();
    }

    @Then("I get a new user {string}")
    public void iGetANewUser(String user) throws InterruptedException {
        Thread.sleep(20000);

        boolean actual = true;
        boolean expected = true;
        String result;

        if (user.equalsIgnoreCase("yes")) {
            result = driver.findElement(By.cssSelector("#signup-success > div > div.content.line.section > section > div > h1")).getText();
            if (result.equalsIgnoreCase("Check your email")) {
                driver.getTitle();
                actual = true;

            } else {
                actual = false;
            }
        }
        if (user.equalsIgnoreCase("no")) {
            expected = false;
            if (driver.findElement(By.id("signup-form")).isDisplayed()) {
                actual = false;
            } else if (driver.findElement(By.linkText("An email address must contain a single @.")).isDisplayed()) {
                actual = false;
            }
            assertEquals(expected, actual);
        }
    }


    @After
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}


