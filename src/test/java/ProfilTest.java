import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import mod.CreateUser;
import mod.LoginUser;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.MainPage;
import pages.ProfilePage;
import utils.UserClient;
import utils.UserGenerator;

import java.util.concurrent.TimeUnit;
@RunWith(Parameterized.class)
public class ProfilTest {

    private String token = "";
    private LoginUser loginData;
    private CreateUser createData;
    private String email;
    private String password;
    private int statusCode;
    private boolean isRegistered;
    private static String shouldBurgerTitle = "Соберите бургер";
    private WebDriver driver;
    private String driverType;

    public ProfilTest(String driverType){
        this.driverType = driverType;
        System.setProperty(
                "webdriver.yandex.driver",
                "src\\main\\resources\\drivers\\" + this.driverType
        );
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.get("https://stellarburgers.nomoreparties.site");
    }

    @Parameterized.Parameters(name="driver: {0}")
    public static Object[][] getDriver(){
        return new Object[][]{
                {"chromedriver.exe"},
                {"yandexdriver.exe"}

        };
    }
    @Before
    public void createAccount(){
        loginData = UserGenerator.getDefaultLoginData();
        createData = UserGenerator.getDefaultRegistrationData();
        ValidatableResponse responseRegister = UserClient.createUser(createData);
        token = responseRegister.extract().path("accessToken");
        statusCode = responseRegister.extract().statusCode();
        isRegistered = responseRegister.extract().path("success");
        loginData = UserGenerator.getDefaultLoginData();
    }


    @Test
    @DisplayName("Переход из профиля в конструктор")
    public void goToConstructorInPersonalAreaPage(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLoginOrderButton();

        loginPage.sendEmail(loginData.getEmail());
        loginPage.sendPassword(loginData.getPassword());
        loginPage.clickButtonLogin();
        loginPage.clickButtonPersonalAccount();

        ProfilePage profilePage= new ProfilePage(driver);
        profilePage.clickButtonConstructor();
        boolean isDisplayedConstructor= profilePage.isDisplayedConstructor();
        Assert.assertTrue(isDisplayedConstructor);

    }
    @Test
    @DisplayName("Переход на главную страницу  через кнопку Логотипа в хедере сайта")
    public void goToLogoHeadInPersonalAreaPage(){

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLoginOrderButton();

        loginPage.sendEmail(loginData.getEmail());
        loginPage.sendPassword(loginData.getPassword());
        loginPage.clickButtonLogin();
        loginPage.clickButtonPersonalAccount();

        ProfilePage profilePage= new ProfilePage(driver);
        profilePage.clickButtonLogoInHeader();
        boolean isDisplayedHeadPage= loginPage.headPageIsDisplayed();
        Assert.assertTrue(isDisplayedHeadPage);
    }
    @Test
    @DisplayName("Выход из аккаунта")
    public void logoutAccount() throws InterruptedException {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLoginOrderButton();

        loginPage.sendEmail(loginData.getEmail());
        loginPage.sendPassword(loginData.getPassword());
        loginPage.clickButtonLogin();

        loginPage.clickButtonPersonalAccount();
        ProfilePage profilePage= new ProfilePage(driver);
        TimeUnit.SECONDS.sleep(2);
        profilePage.clickButtonLogout();
        TimeUnit.SECONDS.sleep(2);
        boolean isDisplayedPageLogin=loginPage.isDisplayedButtonLogin();
        Assert.assertTrue(isDisplayedPageLogin);
    }

    @After
    public void tearDown(){
        ValidatableResponse responseDelete = UserClient.deleteUser(token);
        driver.quit();
    }
}