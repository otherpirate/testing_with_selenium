package br.com.caelum;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UsuarioPage {
	
	private WebDriver driver;
	
	public UsuarioPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void visita(){
		driver.get("http://localhost:8080/usuarios");
	}
	
	public NovoUsuarioPage novo(){
		driver.findElement(By.linkText("Novo Usuário")).click();		
		return new NovoUsuarioPage(driver);
	}
	
	public boolean existeNaListagem(String nome, String email){
		return driver.getPageSource().contains(email) &&
    			driver.getPageSource().contains(email);
	}

    public void deletaUsuarioNaPosicao(int posicao) {
       driver.findElements(By.tagName("button")).get(posicao-1).click();
       Alert alert = driver.switchTo().alert();
       alert.accept();
    }
}
