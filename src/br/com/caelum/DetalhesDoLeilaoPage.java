package br.com.caelum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DetalhesDoLeilaoPage {
	private WebDriver driver;

	public DetalhesDoLeilaoPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void lance(String nome, double valor){
		Select cbUsuario = new Select(driver.findElement(By.name("lance.usuario.id")));
		WebElement txtvalor = driver.findElement(By.name("lance.valor"));
		
		cbUsuario.selectByVisibleText(nome);
		txtvalor.sendKeys(String.valueOf(valor));
		
		WebElement btDarLance = driver.findElement(By.id("btnDarLance"));
		btDarLance.click();
	}

	public boolean existeLance(String nome, double valor){
		Boolean temUsuario = new WebDriverWait(driver, 10)
				.until(ExpectedConditions.textToBePresentInElement(By.id("lancesDados"), nome));
		
		if (temUsuario){
			return driver.getPageSource().contains(String.valueOf(valor));
		}
		return false;					
	}
}
