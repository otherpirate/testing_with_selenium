package br.com.caelum;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UsuarioPage {

	private WebDriver driver;

	public UsuarioPage(WebDriver driver) {
		this.driver = driver;
	}

	public void visita() {
		driver.get(new URLDaAplicacao().getUrlBase() + "usuarios");
	}

	public NovoUsuarioPage novo() {
		driver.findElement(By.linkText("Novo Usuário")).click();
		return new NovoUsuarioPage(driver);
	}

	public AlterarUsuarioPage altera(int posicao) {
		driver.findElements(By.linkText("editar")).get(posicao - 1).click();
		return new AlterarUsuarioPage(driver);
	}

	public boolean existeNaListagem(String nome, String email) {
		return driver.getPageSource().contains(nome)
				&& driver.getPageSource().contains(email);
	}

	public void deletaUsuarioNaPosicao(int posicao) {
		driver.findElements(By.tagName("button")).get(posicao - 1).click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
}
