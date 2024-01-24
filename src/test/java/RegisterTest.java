import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import mod.LoginUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.MainPage;
import pages.RegistrationPage;
import utils.UserClient;
import utils.UserGenerator;


@RunWith(Parameterized.class)
public class RegisterTest {

    private WebDriver driver;
    private String driverType;
    private String token = "";
    private LoginUser loginData;

    public RegisterTest(String driverType){
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
    }

    @Test
    @DisplayName("Проверка успешной регистрации")
    public void successRegistration(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickButtonPersonalAccount();


        RegistrationPage registrationPage=new RegistrationPage(driver);
        registrationPage.clickButtonRegister();

        registrationPage.setName(loginData.getName());
        registrationPage.setEmail(loginData.getEmail());
        registrationPage.setPass(loginData.getPassword());

        registrationPage.clickButtonRegistration();
        registrationPage.waitVisibleAuthText();
        registrationPage.shouldAuthText("Вход");


        ValidatableResponse responseRegister = UserClient.loginUser(loginData);
        token = responseRegister.extract().path("accessToken");
    }
    @Test
    @DisplayName("Проверка регистрации с некорректным паролем")
    public void registrationIncorrectPassTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickButtonPersonalAccount();

        RegistrationPage registrationPage=new RegistrationPage(driver);
        registrationPage.clickButtonRegister();

        registrationPage.setName(loginData.getName());
        registrationPage.setEmail(loginData.getEmail());
        registrationPage.setPass("12");

        registrationPage.clickButtonRegistration();
        registrationPage.shouldIncorrectPassError("Некорректный пароль");
    }
    @After
    public void tearDown(){
        if (token != null)
            UserClient.deleteUser(token);
        driver.quit();
    }

}