import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ShopPage {
    WebDriver driver;
    public  ShopPage(WebDriver driver) {this.driver = driver;}
    private By shoppingCart = By.xpath("//a[@class = 'shopping_cart_link']");
    private By sortDropDown = By.xpath("//select[@class = 'product_sort_container']");


    public List<Float> getPrice(){
        List<WebElement> junctionPriceList = driver.findElements(By.xpath("//div[@class = 'inventory_item_price']"));
        List<Float> priceList = new ArrayList<>();
        for (WebElement Price :junctionPriceList) {
            priceList.add(Float.parseFloat(Price.getText().substring(1)));
        }
        System.out.println(priceList.toString());
        return priceList;
    }

    public ShopPage fromCheapToExpensive(){
        driver.findElement(sortDropDown).click();
        driver.findElement(By.xpath("//option[3]")).click();
        System.out.println("sort: from lower to higher");
        List<Float> priceList= getPrice();
        for (int i = 0; i < priceList.size() - 1 ; i++) {
            System.out.println(priceList.get(i) + "     " + priceList.get(i+1));
            assert (priceList.get(i) <= priceList.get(i+1));
        }
        return this;
    }

    public ShopPage fromExpensiveToCheap(){
        driver.findElement(sortDropDown).click();
        driver.findElement(By.xpath("//option[4]")).click();
        System.out.println("sort: from higher to lower");
        List<Float> priceList= getPrice();
        for (int i = 0; i < priceList.size() - 1; i++) {
            System.out.println(priceList.get(i) + "     " + priceList.get(i+1));
            assert  (priceList.get(i) >= priceList.get(i+1));
        }
        return this;
    }

    public int randomNumberOfItems(){
        Random random = new Random();
        int randomNumber = random.nextInt(6);
        System.out.println("Random number of chosen items: " + randomNumber);
        return randomNumber;
    }

    public List<Integer> randomChosenItems(){
        List<Integer> randomIndexList = new ArrayList<>();
        Random randomGenerator = new Random();
        while (randomIndexList.size() < 6) {

            int random = randomGenerator .nextInt(6);
            if (!randomIndexList.contains(random)) {
                randomIndexList.add(random);
            }
        }
//        System.out.println(randomIndexList);
        return randomIndexList;
    }

    public List<String> getItemsNames(){
        List<WebElement> junctionNamesList = driver.findElements(By.xpath("//div[@class = 'inventory_item_name']"));
        List<String> nameList = new ArrayList<>();
        for (WebElement Name :junctionNamesList) {
            nameList.add(Name.getText());
        }
//        System.out.println(nameList);
        return nameList;

    }
    public List<String> addItems(){
        List<WebElement> addButton = driver.findElements(By.xpath("//button[text() = 'Add to cart']"));
        int randomNumber = this.randomNumberOfItems();
        List<String> nameList = this.getItemsNames();
        List<Integer> randomIndexList = this.randomChosenItems();
        List<String> chosenItemsNames = new ArrayList<>();
        for (int i = 0; i <= randomNumber - 1; i++) {
            int randomIndex = randomIndexList.get(i);
            System.out.println("Index: " + randomIndex);
            addButton.get(randomIndex).click();
            String item = nameList.get(randomIndex);
            System.out.println(item);
            chosenItemsNames.add(item);
        }
//        System.out.println("Added to cart: " + chosenItemsNames);
        return chosenItemsNames;
    }

    public ShopPage openShopCart(){
        driver.findElement(shoppingCart).click();
        System.out.println("Open shop cart");
        return this;
    }

}
