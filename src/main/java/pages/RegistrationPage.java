package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class RegistrationPage {
    private final WebDriver driver;
    private final By nameField = By.xpath(".//label[contains(text(),'Имя')]/../input");
    private final By emailField =By.xpath("//label[contains(text(),'Email')]/../input");
    private final By passwordField = By.xpath(".//label[contains(text(),'Пароль')]/../input");
    private final By registrationButton_1 = By.xpath("//button[text()='Зарегистрироваться']");
    private final By loginButton =By.xpath(".//a[@href='/login']");
    private final By messageErrorPassword = By.xpath(".//p[contains(text(),'Некорректный пароль')]");
    //private final By logo = By.className("AppHeader_header__logo__2D0X2");
    private final By authText = By.xpath("//h2[text()='Вход']");

    private By registrationButton = By.xpath(".//a[contains(text(),'Зарегистрироваться')]");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setName(String name){
        driver.findElement(nameField).click();
        driver.findElement(nameField).sendKeys(name);
    }

    public String setEmail(String email){
        driver.findElement(emailField).click();
        driver.findElement(emailField).sendKeys(email);
        return email;
    }

    public String setPass(String password){
        driver.findElement(passwordField).click();
        driver.findElement(passwordField).sendKeys(password);
        return password;
    }

    public void clickButtonRegistration(){
        driver.findElement(registrationButton_1).click();
    }

    public void clickButtonRegister() {
        driver.findElement(registrationButton).click();
    }

    public void shouldAuthText(String value) {
        Assert.assertEquals(value, driver.findElement(authText).getText());
    }


    public void shouldIncorrectPassError(String value) {
        Assert.assertEquals(value, driver.findElement(messageErrorPassword).getText());
    }



//    public void clickLogoSite(){
//        driver.findElement(logo).click();
//    }

    public void clickButtonLogin(){
        driver.findElement(loginButton).click();
    }

    public void waitVisibleAuthText() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(authText));
    }

}