import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import mod.CreateUser;
import mod.LoginUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.LoginPage;
import pages.MainPage;
import pages.RegistrationPage;
import utils.UserClient;
import utils.UserGenerator;


@RunWith(Parameterized.class)
public class AuthTest {


    private WebDriver driver;
    private String driverType;
    private String token = "";
    private LoginUser loginData;
    private CreateUser createData;
    private int statusCode;
    private boolean isRegistered;

    public AuthTest(String driverType){
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
    public void setUp() {
        loginData = UserGenerator.getDefaultLoginData();
        createData = UserGenerator.getDefaultRegistrationData();
        ValidatableResponse responseRegister = UserClient.createUser(createData);
        token = responseRegister.extract().path("accessToken");
        statusCode = responseRegister.extract().statusCode();
        isRegistered = responseRegister.extract().path("success");
        loginData = UserGenerator.getDefaultLoginData();
    }


    @Test
    @DisplayName("Авторизация через кнопку на главном экране")
    public void authMainButtonTest() {
        //MainPage mainPage = new MainPage(driver);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLoginOrderButton();

        loginPage.sendEmail(loginData.getEmail());
        loginPage.sendPassword(loginData.getPassword());
        loginPage.clickButtonLogin();

        loginPage.clickButtonPersonalAccount();
        loginPage.waitVisibleProfileText();
        loginPage.shouldProfileText();
    }

    @Test
    @DisplayName("Авторизация через кнопку Личный кабинет")
    public void loginButtonPersonalAccountForMainPage(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickButtonPersonalAccount();

        LoginPage loginPage = new LoginPage(driver);

        loginPage.sendEmail(loginData.getEmail());
        loginPage.sendPassword(loginData.getPassword());
        loginPage.clickButtonLogin();

        loginPage.clickButtonPersonalAccount();
        loginPage.waitVisibleProfileText();
        loginPage.shouldProfileText();
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void authRegistrationPage(){
        //MainPage mainPage = new MainPage(driver);
        //mainPage.clickButtonLogin();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLoginOrderButton();
        loginPage.clickButtonRegister();

        RegistrationPage registrationPage =new RegistrationPage(driver);
        registrationPage.clickButtonLogin();
        loginPage.sendEmail(loginData.getEmail());
        loginPage.sendPassword(loginData.getPassword());
        loginPage.clickButtonLogin();

        loginPage.clickButtonPersonalAccount();
        loginPage.waitVisibleProfileText();
        loginPage.shouldProfileText();
    }
    @Test
    @DisplayName("Авторизация через кнопку в форме восстановления пароля")
    public void authForgotPasswordPage(){
//        MainPage objHeadPage = new MainPage(driver);
//        objHeadPage.clickButtonLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLoginOrderButton();
        loginPage.clickButtonRecoverPassword();

        RegistrationPage registrationPage =new RegistrationPage(driver);
        registrationPage.clickButtonLogin();

        loginPage.sendEmail(loginData.getEmail());
        loginPage.sendPassword(loginData.getPassword());
        loginPage.clickButtonLogin();

        loginPage.clickButtonPersonalAccount();
        loginPage.waitVisibleProfileText();
        loginPage.shouldProfileText();
    }



    @After
    public void tearDown(){
        ValidatableResponse responseDelete = UserClient.deleteUser(token);
        driver.quit();
    }
}