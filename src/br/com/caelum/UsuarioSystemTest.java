package br.com.caelum;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class UsuarioSystemTest {
	private WebDriver driver;
	private UsuarioPage usuarioPage;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "lib/chromedriver");
		driver = new ChromeDriver();
		driver.get(new URLDaAplicacao().getUrlBase() + "apenas-teste/limpa");
		
		usuarioPage = new UsuarioPage(driver);
		usuarioPage.visita();
	}

	@After
	public void finalizar() {
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
		String nome = "";
		String email = "joao@email.com";
		usuarioPage.novo().cadastra(nome, email);

		Assert.assertTrue(driver.getPageSource().contains("Nome obrigatorio!"));
	}

	@Test
	public void naoDeveAdicionarUsuarioSemEmail() {
		String nome = "João";
		String email = "";
		usuarioPage.novo().cadastra(nome, email);

		Assert.assertTrue(driver.getPageSource()
				.contains("E-mail obrigatorio!"));
	}

	@Test
	public void naoDeveAdicionarUsuarioSemNomeESemEmail() {
		String nome = "";
		String email = "";
		usuarioPage.novo().cadastra(nome, email);

		Assert.assertTrue(driver.getPageSource().contains("Nome obrigatorio!"));
		Assert.assertTrue(driver.getPageSource()
				.contains("E-mail obrigatorio!"));
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

	@Test
	public void deveAtualizarUmUsuario() {
		String nome = "João";
		String email = "joao@email.com";
		String nomeNovo = "Mauro";
		String emailNovo = "Mauro@email.com";

		usuarioPage.novo().cadastra(nome, email);
		Assert.assertTrue(usuarioPage.existeNaListagem(nome, email));
		usuarioPage.altera(1).para(nomeNovo, emailNovo);

		Assert.assertFalse(usuarioPage.existeNaListagem(nome, email));
		Assert.assertTrue(usuarioPage.existeNaListagem(nomeNovo, emailNovo));
	}
}
