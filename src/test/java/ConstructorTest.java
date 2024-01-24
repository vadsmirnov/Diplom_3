import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.MainPage;


@RunWith(Parameterized.class)
public class ConstructorTest {
    private WebDriver driver;
    private String driverType;

    public ConstructorTest(String driverType){
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


    @Test
    @DisplayName("Переход к разделу Булки")
    public void checkBun() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickButtonFillings();
        mainPage.clickButtonBun();
        mainPage.shouldBunTab();

    }

    @Test
    @DisplayName("Переход к разделу Соусы")
    public void checkSause() {

        MainPage mainPage = new MainPage(driver);
        mainPage.clickButtonSause();
        mainPage.shouldSouseTab();

    }
    @Test
    @DisplayName("Переход к разделу Начинки")
    public void checkFillings() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickButtonFillings();
        mainPage.shouldFillingTab();

    }
    @After
    public void tearDown(){
        driver.quit();
    }


}
