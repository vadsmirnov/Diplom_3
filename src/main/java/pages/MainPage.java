package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {

    private WebDriver driver;
    private By bunTab = By.xpath("//span[text()='Булки']");
    private By sauseTab = By.xpath("//span[text()='Соусы']");
    private By fillingTab = By.xpath("//span[text()='Начинки']");
    //private By buttonConstructor = By.xpath(".//p[contains(text(),'Конструктор')]");
    private By buttonPersonalAccount = By.xpath("//p[text()='Личный Кабинет']");
    //private By buttonLogin = By.xpath(".//button[contains(text(),'Войти в аккаунт')]");

    private By mainClass = By.xpath(".//main[@class='App_componentContainer__2JC2W']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    public String getTabBuns() {
        return driver.findElement(bunTab).getText();
    }
    public void clickButtonSause() {
        driver.findElement(sauseTab).click();
    }
    public void clickButtonFillings() {
        driver.findElement(fillingTab).click();
    }
    public void clickButtonPersonalAccount() {
        driver.findElement(buttonPersonalAccount).click();
    }
//    public void clickButtonLogin() {
//        driver.findElement(buttonLogin).click();
//    }

    public boolean isDisplayedHeadPage() {
        return driver.findElement(mainClass).isDisplayed();
    }
    public void clickButtonBun() {
        driver.findElement(bunTab).click();
    }
    public String getTabSause() {
        return driver.findElement(sauseTab).getText();
    }
    public String getTabFillings() {
        return driver.findElement(fillingTab).getText();
    }
}