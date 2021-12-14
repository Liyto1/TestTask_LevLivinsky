import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private ShopPage shopPage;


    @Before
    public void setUp(){
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Lev\\IdeaProjects\\drivers\\firefox\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        System.out.println("Open browser");
        loginPage = new LoginPage(driver);
        loginPage.Login();
        shopPage = new ShopPage(driver);
    }
    @Test
    public void checkingSorts(){
        shopPage.fromCheapToExpensive();
        shopPage.fromExpensiveToCheap();
        System.out.println("Completed checking sorts 'from expensive to cheap' and 'from cheap  to expensive'");
    }
    @Test
    public void checkingAddedItems(){
        List<String> chosenItemsNames = this.shopPage.addItems();
        shopPage.openShopCart();
        List<String> itemsInCart = this.shopPage.getItemsNames();
        System.out.println("Added to cart: " + chosenItemsNames);
        System.out.println("Items in a cart: " + itemsInCart);
        assert (chosenItemsNames.equals(itemsInCart));

    }
    @After
    public  void tearDown(){
        System.out.println("Close browser");
        driver.quit();
    }
}
