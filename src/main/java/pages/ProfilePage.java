package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage {
    private WebDriver driver;
    //private By messageInPagePersonalArea=By.xpath(".//a[contains(text(),'Профиль')]");
    private By constructorButton =By.xpath("//p[text()='Конструктор']");
    private By logo = By.className("AppHeader_header__logo__2D0X2");
    private By exitButton = By.xpath("//button[text()='Выход']");
    private By listConstructor = By.className("BurgerIngredients_ingredients__menuContainer__Xu3Mo");


    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }
    //    public boolean isDisplayedMessage(){
//        return driver.findElement(messageInPagePersonalArea).isDisplayed();
//    }
    public boolean isDisplayedConstructor() {
        return driver.findElement(listConstructor).isDisplayed();
    }
    public void clickButtonConstructor(){
        driver.findElement(constructorButton).click();
    }
    public void clickButtonLogoInHeader(){
        driver.findElement(logo).click();
    }
    public void clickButtonLogout() throws InterruptedException {
        driver.findElement(exitButton).click();
    }
}