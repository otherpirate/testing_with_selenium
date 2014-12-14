package br.com.caelum;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LanceSystemTest {
	private WebDriver driver;
	private DetalhesDoLeilaoPage lances;

	@Before
	public void setUp() {		
		System.setProperty("webdriver.chrome.driver", "lib/chromedriver");
		driver = new ChromeDriver();
		driver.get(new URLDaAplicacao().getUrlBase() + "apenas-teste/limpa");		

        new CriadorDeCenario(driver)
            .umUsuario("Joao", "joao@emao.com")
            .umUsuario("Jose", "jose@emai.com")
            .umLeilao("Joao", "TV 42", 2000, false);
		
		LeilaoPage leilaoPage = new LeilaoPage(driver);
		leilaoPage.visita();		
		lances = leilaoPage.detalhe(1);
	}

	@After
	public void finalizar() {
		driver.close();
	}

	@Test
	public void deveDarLance() {
		String nome = "Jose";
		double valor = 2150.00;
		
		lances.lance(nome, valor);
		Assert.assertTrue(lances.existeLance(nome, valor));
	}
}
