package br.com.caelum;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class UsuarioSystemTest {
	private WebDriver driver;
	private UsuarioPage usuarioPage;

	@Before
	public void setUp(){
		System.setProperty("webdriver.chrome.driver","lib/chromedriver");		
		driver = new ChromeDriver();
		usuarioPage = new UsuarioPage(driver);
		usuarioPage.visita();
	}

	@After
	public void finalizar(){
		driver.close();
	}
	
	@Test
	public void deveAdicionarNovoUsuario() {
		String nome = "João";
		String email = "joao@email.com";
		
		usuarioPage.novo().cadastra(nome, email);
		Assert.assertTrue(usuarioPage.existeNaListagem(nome, email));
	}
	
	@Test
	public void naoDeveAdicionarUsuarioSemNome() {
		WebElement email = driver.findElement(By.name("usuario.email"));
		email.sendKeys("jose@email.com.br");
		email.submit();
		
		Assert.assertTrue(driver.getPageSource().contains("Nome obrigatorio!"));	
	}
	
	@Test
	public void naoDeveAdicionarUsuarioSemEmail() {
		WebElement nome = driver.findElement(By.name("usuario.nome"));
		nome.sendKeys("José");
		nome.submit();

		Assert.assertTrue(driver.getPageSource().contains("E-mail obrigatorio!"));	
	}
	
	@Test
	public void naoDeveAdicionarUsuarioSemNomeESemEmail() {
		WebElement botaoSalvar = driver.findElement(By.id("btnSalvar"));
		botaoSalvar.click();

		Assert.assertTrue(driver.getPageSource().contains("Nome obrigatorio!"));
		Assert.assertTrue(driver.getPageSource().contains("E-mail obrigatorio!"));	
	}
	
	@Test
    public void deveDeletarUmUsuario() {
		String nome = "João";
		String email = "joao@email.com";
		
		usuarioPage.novo().cadastra(nome, email);
        Assert.assertTrue(usuarioPage.existeNaListagem(nome, email));

        usuarioPage.deletaUsuarioNaPosicao(1);

        Assert.assertFalse(usuarioPage.existeNaListagem(nome, email));
    }
}
