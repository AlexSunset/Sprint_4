package page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MainPage {

    //Кнопка заказать вверху страницы
    private final By orderButtonTop = By.className("Button_Button__ra12g");

    /*Раздел с локаторами*/
    //Кнопка заказать внизу страницы
    private final By orderButtonBottom = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button");
    //Блок с вопросами
    private final By questionSection = By.className("accordion");
    //Список вопросов
    private final By questionList = By.className("accordion__item");
    //Список ответов
    private final By answerList = By.xpath(".//div[@class='accordion__panel']/p");
    private final WebDriver driver;

    //Конструктор
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    /*Раздел с методами*/

    //Нажать на кнопку Заказать вверху страницы
    public void clickOnOrderButtonTop() {
        driver.findElement(orderButtonTop).click();
    }

    //Нажать на кнопку Заказать внизу страницы
    public void clickOnOrderButtonBottom() {
        WebElement element = driver.findElement(orderButtonBottom);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(orderButtonBottom).click();
    }

    //Прокрутить до блока с вопросами
    public void scrollToQuestionList() {
        WebElement element = driver.findElement(questionSection);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    //Получить все элементы вопросов в списке
    public List<WebElement> questionElements() {
        return driver.findElements(questionList);
    }

    //Получить все элементы ответов в списке
    public List<WebElement> answerElements() {
        return driver.findElements(answerList);
    }
}
