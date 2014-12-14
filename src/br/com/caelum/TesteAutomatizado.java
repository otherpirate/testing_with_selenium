package br.com.caelum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteAutomatizado {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "lib/chromedriver");

		WebDriver driver = new ChromeDriver();
		driver.get("http://www.bing.com");

		WebElement campoDeTexto = driver.findElement(By.name("q"));
		campoDeTexto.sendKeys("Caelum");

		campoDeTexto.submit();
	}
}
