package page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class OrderPage {
    private final WebDriver driver;

    /*Раздел с локаторами*/

    //Поле ввода имени
    private final By inputName = By.xpath("//input[@placeholder='* Имя']");
    //Поле ввода фамилии
    private final By inputSurname = By.xpath("//input[@placeholder='* Фамилия']");
    //Поле ввода адреса
    private final By inputAddress = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    //Поле ввода станции метро
    private final By inputStation = By.xpath("//input[@placeholder='* Станция метро']");
    //Список элементов всех станций метро
    private final By liStation = By.xpath("//li[@class='select-search__row']");
    //Поле ввода телефона
    private final By inputPhoneNumber = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Кнопка далее
    private final By buttonNext = By.xpath("//button[text()='Далее']");
    // Поле ввода даты доставки
    private final By inputDate = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    //Поле ввода срока аренды
    private final By inputDuration = By.xpath("//span[@class='Dropdown-arrow']");
    //Список сроков аренды
    private final By durationOptions = By.xpath("//div[@class='Dropdown-option']");
    //Чекбокс чёрный жемчуг
    private final By labelBlack = By.xpath("//label[@for='black']");
    //Поле с комментарием
    private final By inputComment = By.xpath("//input[@placeholder='Комментарий для курьера']");
    //Кнопка Заказать(на последнем шаге)
    private final By orderButtonFinal = By.xpath("//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");
    //Кнопка Да на форме "Хотите оформить заказ?"
    private final By confirmOrderButton = By.xpath("//div[@class='Order_Buttons__1xGrp']/button[text()='Да']");
    //Заголовок на форме с результами заказа
    private final By headerOrderResult = By.xpath("//div[@class='Order_ModalHeader__3FDaJ']");

    //Конструктор
    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    /*Раздел с методами*/

    //Заполнить поле с именем
    public void enterName(String name) {
        driver.findElement(inputName).clear();
        driver.findElement(inputName).sendKeys(name);
    }

    //Заполнить поле с фамилией
    public void enterSurname(String surname) {
        driver.findElement(inputSurname).clear();
        driver.findElement(inputSurname).sendKeys(surname);
    }

    //Заполнить поле с адресом
    public void enterAddress(String address) {
        driver.findElement(inputAddress).clear();
        driver.findElement(inputAddress).sendKeys(address);
    }

    //Заполнить поле со станцией метро
    public void enterStation(int stationNumber) {
        driver.findElement(inputStation).click();
        List<WebElement> stationList = driver.findElements(liStation);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", stationList.get(stationNumber));
        stationList.get(stationNumber).click();
    }

    //Заполнить поле с телефоном
    public void enterPhoneNumber(String phone) {
        driver.findElement(inputPhoneNumber).clear();
        driver.findElement(inputPhoneNumber).sendKeys(phone);
    }

    //Кликнуть на кнопку далее
    public void clickOnNextButton() {
        driver.findElement(buttonNext).click();
    }

    //Полное заполнение формы "Для кого самокат" и нажатие на "Далее"
    public void forWhomFormFillingAndNext(String name, String surname, String address, int stationIndex, String phone) {
        enterName(name);
        enterSurname(surname);
        enterAddress(address);
        enterStation(stationIndex);
        enterPhoneNumber(phone);
        clickOnNextButton();
    }

    //Заполнить поля даты доставки
    public void enterDate(String date) {
        driver.findElement(inputDate).clear();
        driver.findElement(inputDate).sendKeys(date);
    }

    //Заполнить поле срок аренды
    public void enterDuration(int index) {
        driver.findElement(inputDuration).click();
        List<WebElement> durationList = driver.findElements(durationOptions);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", durationList.get(index));
        durationList.get(index).click();
    }

    //Указать чёрный цвет самоката
    public void setBlackColor() {
        driver.findElement(labelBlack).click();
    }

    //Заполнить поле с комментарием
    public void enterComment(String comment) {
        driver.findElement(inputComment).clear();
        driver.findElement(inputComment).sendKeys(comment);
    }

    //Нажать кнопку Заказать на последнем шаге
    public void clickOrderButtonFinal() {
        driver.findElement(orderButtonFinal).click();
    }

    //Заполнить форму "Про аренду" и нажать "Заказать"
    public void aboutRentFormFillAndOrder(String date, int durationIndex, String comment) {
        enterDate(date);
        enterDuration(durationIndex);
        setBlackColor();
        enterComment(comment);
        clickOrderButtonFinal();
    }

    //Нажать на кнопку подтвержения заказа
    public void clickOnConfirmOrderButton() {
        driver.findElement(confirmOrderButton).click();
    }

    //Получить текст формы с результатами заказа
    public String getTextFromOrderResult() {
        return driver.findElement(headerOrderResult).getText();
    }
}
