package pages;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {

    private WebDriver driver;
    private By bunTab = By.xpath("//span[text()='Булки']");
    private By sauseTab = By.xpath("//span[text()='Соусы']");
    private By fillingTab = By.xpath("//span[text()='Начинки']");
    private By buttonPersonalAccount = By.xpath("//p[text()='Личный Кабинет']");
    private By mainClass = By.xpath(".//main[@class='App_componentContainer__2JC2W']");
    private final By souseText = By.xpath("//div[span[text()='Соусы']]");
    private final By bunText = By.xpath("//div[span[text()='Булки']]");
    private final By fillingText = By.xpath("//div[span[text()='Начинки']]");

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

    public void shouldSouseTab() {
        Assert.assertTrue(driver.findElement(souseText).getAttribute("class").contains("current"));
    }
    public void shouldBunTab() {
        Assert.assertTrue(driver.findElement(bunText).getAttribute("class").contains("current"));
    }
    public void shouldFillingTab() {
        Assert.assertTrue(driver.findElement(fillingText).getAttribute("class").contains("current"));
    }
}