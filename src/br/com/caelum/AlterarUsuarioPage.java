package br.com.caelum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AlterarUsuarioPage {
	private WebDriver driver;

	public AlterarUsuarioPage(WebDriver driver) {
		this.driver = driver;
	}

	public UsuarioPage para(String nome, String email) {
		WebElement txtNome = driver.findElement(By.name("usuario.nome"));
		WebElement txtEmail = driver.findElement(By.name("usuario.email"));

		txtNome.clear();
		txtNome.sendKeys(nome);
		txtEmail.clear();
		txtEmail.sendKeys(email);

		txtNome.submit();
		return new UsuarioPage(driver);
	}
}
