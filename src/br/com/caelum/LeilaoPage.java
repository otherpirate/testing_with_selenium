package br.com.caelum;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LeilaoPage {
	private WebDriver driver;

	public LeilaoPage(WebDriver driver) {
		this.driver = driver;
	}

	public void visita() {
		driver.get(new URLDaAplicacao().getUrlBase() + "leiloes");
	}

	public NovoLeilaoPage novo() {
		driver.findElement(By.linkText("Novo Leilão")).click();
		return new NovoLeilaoPage(driver);
	}

	public boolean existeNaListagem(String nome, double valor, String usuario,
			boolean usado) {
		return driver.getPageSource().contains(nome)
				&& driver.getPageSource().contains(String.valueOf(valor))
				&& driver.getPageSource().contains(usuario)
				&& driver.getPageSource().contains(usado ? "Sim" : "Não");
	}

	public DetalhesDoLeilaoPage detalhe(int posicao) {
		List<WebElement> elementos = driver.findElements(By.linkText("exibir"));
		elementos.get(posicao-1).click();;
		
		return new DetalhesDoLeilaoPage(driver);
	}
}
