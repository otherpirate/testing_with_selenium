package br.com.caelum;

import org.openqa.selenium.WebDriver;

public class CriadorDeCenario {

    private WebDriver driver;

    public CriadorDeCenario(WebDriver driver) {
        this.driver = driver;
    }

    public CriadorDeCenario umUsuario(String nome, String email) {
        UsuarioPage usuarios = new UsuarioPage(driver);
        usuarios.visita();
        usuarios.novo().cadastra(nome, email);

        return this;
    }

    public CriadorDeCenario umLeilao(String usuario, 
                String produto, 
                double valor, 
                boolean usado) {
        LeilaoPage leiloes = new LeilaoPage(driver);
        leiloes.visita();
        leiloes.novo().preenche(produto, valor, usuario, usado);

        return this;
    }
}
