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
	    // ��������� ������� � ��������� �� ����� ������.������
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://yandex.ru/pogoda");
        
        //test 1 - �������� �������� � ������� ������ (������)
        WebElement element = driver.findElement(By.xpath("//*[@id=\"header2input\"]"));
        element.sendKeys("������");
        element.submit();
        
        element = driver.findElement(By.linkText("������, ������ � ���������� �������"));
        Actions builder = new Actions(driver);
        builder.click(element);
        builder.build().perform();
        
        element = driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[1]/div[1]/div/div[1]/h1"));
        
        if (element.getText().equals("������ � ������"))
        	results[0] = "���� �1 (����� ������ � ������) - �������";
        else
        	results[0] = "���� �1 (����� ������ � ������) - �� �������";
        
        //test 2 - �������� ��������� ������ � ������ �������
        element = driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[1]/div[1]/div/div[1]/h1/span/div/i"));
        builder.click(element);
        builder.build().perform();
        
        driver.navigate().to("https://yandex.ru/pogoda");
        element = driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[1]/div[1]/div/div[1]/h1/span/div/i"));
        builder.click(element);
        builder.build().perform();
        
        element = driver.findElement(By.linkText("��������� �������"));
        builder.click(element);
        builder.build().perform();
        
        element = driver.findElement(By.xpath("/html/body/div[6]/section[1]/h1"));
        if (element.getText().equals("��������� �������"))
        	results[1] = "���� �2 (��������� ������ � ������ �������) - �������";
        else
        	results[1] = "���� �2 (��������� ������ � ������ �������) - �� �������";
        
        // test 3 - �������� ������ ������ "������� �� 10 ����", "������� �� �����"
        boolean isTest30 = false, isTest10 = false;
        
        driver.navigate().to("https://yandex.ru/pogoda");
        element = driver.findElement(By.linkText("������� �� �����"));
        builder.click(element);
        builder.build().perform();
        
        element = driver.findElement(By.xpath("/html/body/div[6]/section[1]/div[1]/div[2]/div/div[1]"));
        if (element.getText().equals("� ������� �� 30 ����"))
        	isTest30 = true;
        else 
        	isTest30 = false;
        
        element = driver.findElement(By.linkText("������� �� 10 ����"));
        builder.click(element);
        builder.build().perform();
        
        element = driver.findElement(By.xpath("/html/body/div[6]/div[2]/header/h1"));
        if (element.getText().equals("������� �� 10 ����"))
        	isTest10 = true;
        else 
        	isTest10 = false;
        
        if (isTest30 && isTest10)
        	results[2] = "���� �3 (�������� ������ ������ \"������� �� 10 ����\", \"������� �� �����\") - �������";
        else
        	results[2] = "���� �3 (�������� ������ ������ \"������� �� 10 ����\", \"������� �� �����\") - �� �������";
        
        // ��������� �������
        driver.quit();
        
        // ������������� ���������� ������
        System.out.println("���������� ������������");
        for (int i=0; i<results.length; ++i)
        	System.out.println(results[i]);

        

	}

}
