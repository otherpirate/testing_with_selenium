package br.com.caelum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class NovoLeilaoPage {

	private final WebDriver driver;

	public NovoLeilaoPage(WebDriver driver) {
		this.driver = driver;
	}

	public void preenche(String nome, double valor, String usuario,
			boolean usado) {
		WebElement txtNome = driver.findElement(By.name("leilao.nome"));
		WebElement txtValor = driver
				.findElement(By.name("leilao.valorInicial"));
		Select cbUsuario = new Select(driver.findElement(By
				.name("leilao.usuario.id")));
		WebElement ckUsado = driver.findElement(By.name("leilao.usado"));

		txtNome.sendKeys(nome);
		txtValor.sendKeys(String.valueOf(valor));
		cbUsuario.selectByVisibleText(usuario);

		if (usado)
			ckUsado.click();

		txtNome.submit();
	}
}
