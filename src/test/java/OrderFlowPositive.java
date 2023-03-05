import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page_object.MainPage;
import page_object.OrderPage;
import page_object.UrlConstants;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.containsString;

@RunWith(Parameterized.class)
public class OrderFlowPositive {

    private final String name;
    private final String surname;
    private final String address;
    private final int stationIndex;
    private final String phone;
    private final String date;
    private final int durationIndex;
    private final String comment;
    private WebDriver driver;

    public OrderFlowPositive(String name, String surname,
                             String address, int stationIndex, String phone,
                             String date, int durationIndex, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.stationIndex = stationIndex;
        this.phone = phone;
        this.date = date;
        this.durationIndex = durationIndex;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {"Александр", "Закатов", "Первый московский адрес", 2, "89023456789", "04.03.23", 0, "Первый коммент"},
                {"Иван", "Иванов", "Второй московский адрес", 5, "89773456777", "05.05.24", 1, "Второй коммент"},
        };
    }
    //Проверка основного потока по верхней кнопке "Заказать"
    @Test
    public void flowOnTopOrderButton() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://qa-scooter.praktikum-services.ru");
        driver.manage().window().maximize();
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnOrderButtonTop();
        OrderPage orderPage = new OrderPage(driver);
        orderPage.forWhomFormFillingAndNext(name, surname, address, stationIndex, phone);
        orderPage.aboutRentFormFillAndOrder(date, durationIndex, comment);
        orderPage.clickOnConfirmOrderButton();
        MatcherAssert.assertThat(orderPage.getTextFromOrderResult(), containsString("Номер заказа"));
    }
    //Проверка основного потока по нижней кнопке "Заказать"
    @Test
    public void flowOnBottomOrderButton() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(UrlConstants.SITE_PAGE);
        driver.manage().window().maximize();
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnOrderButtonBottom();
        OrderPage orderPage = new OrderPage(driver);
        orderPage.forWhomFormFillingAndNext(name, surname, address, stationIndex, phone);
        orderPage.aboutRentFormFillAndOrder(date, durationIndex, comment);
        orderPage.clickOnConfirmOrderButton();
        MatcherAssert.assertThat(orderPage.getTextFromOrderResult(), containsString("Номер заказа"));
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
