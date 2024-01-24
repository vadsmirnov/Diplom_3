package pages;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver driver;
    private By emailField = By.xpath(".//input[@type='text']");
    private By passwordField = By.xpath(".//input[@type='password']");
    private By loginButton = By.xpath("//button[text()='Войти']");
    private By registrationButtonLoginPage = By.xpath(".//a[contains(text(),'Зарегистрироваться')]");
    private By buttonRecoverPassword = By.xpath("//a[text()='Восстановить пароль']");
    private By mainClass = By.xpath(".//main[@class='App_componentContainer__2JC2W']");

    private By loginOrderButton = By.xpath(".//button[contains(text(),'Войти в аккаунт')]");

    private By buttonPersonalAccount = By.xpath("//p[text()='Личный Кабинет']");

    private final By profileText = By.xpath("//nav[@class='Account_nav__Lgali']//p[1]");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickButtonRegister() {
        driver.findElement(registrationButtonLoginPage).click();
    }

    public void sendEmail(String email) {
        driver.findElement(emailField).click();
        driver.findElement(emailField).sendKeys(email);
    }

    public void clickButtonPersonalAccount() {
        driver.findElement(buttonPersonalAccount).click();
    }

    public void waitVisibleProfileText() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(profileText));
    }
    public void clickLoginOrderButton() {
        driver.findElement(loginOrderButton).click();
    }
    public void sendPassword(String password) {
        driver.findElement(passwordField).click();
        driver.findElement(passwordField).sendKeys(password);
    }
    public void clickButtonLogin() {
        driver.findElement(loginButton).click();
    }
    public void shouldProfileText() {
        Assert.assertEquals("В этом разделе вы можете изменить свои персональные данные", driver.findElement(profileText).getText());
    }
    public boolean headPageIsDisplayed(){
        return driver.findElement(mainClass).isDisplayed();
    }
    public void clickButtonRecoverPassword(){
        driver.findElement(buttonRecoverPassword).click();
    }
    public boolean isDisplayedButtonLogin(){
        return driver.findElement(loginButton).isDisplayed();
    }
}