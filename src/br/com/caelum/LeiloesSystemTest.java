package br.com.caelum;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LeiloesSystemTest {
	private WebDriver driver;
	private LeilaoPage leilaoPage;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "lib/chromedriver");
		driver = new ChromeDriver();
		driver.get(new URLDaAplicacao().getUrlBase() + "apenas-teste/limpa");

		UsuarioPage usuario = new UsuarioPage(driver);
		usuario.visita();
		usuario.novo().cadastra("José", "jose@email.com");

		leilaoPage = new LeilaoPage(driver);
		leilaoPage.visita();
	}

	@After
	public void finalizar() {
		driver.close();
	}

	@Test
	public void deveAdicionarNovoLeilao() {
		String nome = "Fogão";
		double valor = 500.00;
		String usuario = "José";
		boolean usado = true;

		leilaoPage.novo().preenche(nome, valor, usuario, usado);
		Assert.assertTrue(leilaoPage.existeNaListagem(nome, valor, usuario,
				usado));
	}

	@Test
	public void naoDeveAdicionarLeilaoSemNomeESemValor() {
		String nome = "";
		double valor = 0;
		String usuario = "José";
		boolean usado = true;

		leilaoPage.novo().preenche(nome, valor, usuario, usado);
		Assert.assertTrue(driver.getPageSource().contains("Nome obrigatorio!"));
		Assert.assertTrue(driver.getPageSource().contains(
				"Valor inicial deve ser maior que zero!"));
	}
}
