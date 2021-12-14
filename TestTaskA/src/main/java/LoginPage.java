import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;
    public  LoginPage(WebDriver driver) {this.driver = driver;}
    private By userNameField = By.xpath("//input[@id = 'user-name']");
    private By passwordField = By.xpath("//input[@id = 'password']");
    private By loginButton = By.xpath("//input[@id = 'login-button']");

    public LoginPage Login(){
        driver.findElement(userNameField).sendKeys("standard_user");
        driver.findElement(passwordField).sendKeys("secret_sauce");
        driver.findElement(loginButton).click();
        System.out.println("Login");
        return this;
    }
}
