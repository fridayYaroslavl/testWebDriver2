package testWebDriver2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class MainTest {
	static String[] results = new String[3];

	public static void main(String[] args) {
	    // открываем браузер и переходим на поиск Яндекс.Погоду
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://yandex.ru/pogoda");
        
        //test 1 - проверка перехода к другому городу (Москва)
        WebElement element = driver.findElement(By.xpath("//*[@id=\"header2input\"]"));
        element.sendKeys("Москва");
        element.submit();
        
        element = driver.findElement(By.linkText("Москва, Москва и Московская область"));
        Actions builder = new Actions(driver);
        builder.click(element);
        builder.build().perform();
        
        element = driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[1]/div[1]/div/div[1]/h1"));
        
        if (element.getText().equals("Погода в Москве"))
        	results[0] = "Тест №1 (поиск погоды в Москве) - пройден";
        else
        	results[0] = "Тест №1 (поиск погоды в Москве) - не пройден";
        
        //test 2 - проверка сравнения погоды в разных городах
        element = driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[1]/div[1]/div/div[1]/h1/span/div/i"));
        builder.click(element);
        builder.build().perform();
        
        driver.navigate().to("https://yandex.ru/pogoda");
        element = driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[1]/div[1]/div/div[1]/h1/span/div/i"));
        builder.click(element);
        builder.build().perform();
        
        element = driver.findElement(By.linkText("Сравнение городов"));
        builder.click(element);
        builder.build().perform();
        
        element = driver.findElement(By.xpath("/html/body/div[6]/section[1]/h1"));
        if (element.getText().equals("Сравнение городов"))
        	results[1] = "Тест №2 (сравнение погоды в разных городах) - пройден";
        else
        	results[1] = "Тест №2 (сравнение погоды в разных городах) - не пройден";
        
        // test 3 - проверка работы ссылок "Прогноз на 10 дней", "Прогноз на месяц"
        boolean isTest30 = false, isTest10 = false;
        
        driver.navigate().to("https://yandex.ru/pogoda");
        element = driver.findElement(By.linkText("Прогноз на месяц"));
        builder.click(element);
        builder.build().perform();
        
        element = driver.findElement(By.xpath("/html/body/div[6]/section[1]/div[1]/div[2]/div/div[1]"));
        if (element.getText().equals("В среднем за 30 дней"))
        	isTest30 = true;
        else 
        	isTest30 = false;
        
        element = driver.findElement(By.linkText("Прогноз на 10 дней"));
        builder.click(element);
        builder.build().perform();
        
        element = driver.findElement(By.xpath("/html/body/div[6]/div[2]/header/h1"));
        if (element.getText().equals("Прогноз на 10 дней"))
        	isTest10 = true;
        else 
        	isTest10 = false;
        
        if (isTest30 && isTest10)
        	results[2] = "Тест №3 (проверка работы ссылок \"Прогноз на 10 дней\", \"Прогноз на месяц\") - пройден";
        else
        	results[2] = "Тест №3 (проверка работы ссылок \"Прогноз на 10 дней\", \"Прогноз на месяц\") - не пройден";
        
        // закрываем браузер
        driver.quit();
        
        // распечатываем результаты тестов
        System.out.println("Результаты тестирования");
        for (int i=0; i<results.length; ++i)
        	System.out.println(results[i]);

        

	}

}
