package usuarioTest;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import excecoes.MinhaException;
import jogo.Jogo;
import jogo.Luta;
import jogo.Plataforma;
import jogo.RPG;
import usuario.Veterano;

/**
 * Testes bem gerais que certifica que ambos tipos de usuários noob e veterano estão funcionando de acordo
 * com o esperado, acredito que cobrem as partes mais interessantes de ambas as implementações
 * 
 * @author Gabriel Fernandes
 */
public class VeteranoTest {
	private Veterano expert;
	
	@Before
	public void setUp() throws MinhaException {
		expert = new Veterano("Savannah", "diviners");
		expert.adicionaDinheiro(350);
	}
	
	@Test
	public void testGetLogin() {
		assertEquals("diviners", expert.getLogin());
	}

	@Test
	public void testGetDinheiro() {
		assertTrue(350 == expert.getDinheiro());
	}
	
	@Test
	public void testDepositaDinheiro() throws MinhaException {

		expert.adicionaDinheiro(350);
		assertTrue(700 == expert.getDinheiro());
		
		try {
			expert.adicionaDinheiro(-500);
			fail();
		}
		catch (Exception e) {
			assertEquals("Quantidade de dinheiro nao pode ser negativa ou zero", e.getMessage());
		}
		
		assertTrue(700 == expert.getDinheiro());
	}

	@Test
	public void testAdicionaX2p() {
		try {
			assertEquals(0, expert.getX2p());
			expert.aumentaX2p(500);
		}
		catch (Exception e) {
			assertEquals("valor passado invalido", e.getMessage());
		}

		try {
			expert.aumentaX2p(-500);
			fail();
		}
		catch (Exception e) {
			assertEquals("X2p invalido!", e.getMessage());
		}
	}
	
	@Test
	public void testCompraJogo() throws MinhaException {
		RPG noMansSky = new RPG("No Man's Sky", 150);
		assertTrue(expert.compraJogo(noMansSky));
		assertTrue(230.0 == expert.getDinheiro());
		assertTrue(expert.contemJogo("No Man's Sky"));
		Plataforma failGame = null;
		try {
			expert.compraJogo(failGame);
			Assert.fail();
		}
		catch (Exception e) {
			assertEquals("Jogo nao pode ser nulo", e.getMessage());
		}
	}
	
	@Test
	public void testNoob() throws MinhaException {
		
		try {
			new Veterano(null, "teste");
			fail();
		}catch(Exception e) {
			assertEquals("Nome nao pode ser nulo ou vazio", e.getMessage());
		}
		
		try {
			new Veterano("", "teste");
			fail();
		}catch(Exception e) {
			assertEquals("Nome nao pode ser nulo ou vazio", e.getMessage());
		}
		
		try {
			new Veterano("teste", null);
			fail();
		}catch(Exception e) {
			assertEquals("login nao pode ser nulo ou vazio", e.getMessage());
		}
		
		try {
			new Veterano("teste", "");
			fail();
		}catch(Exception e) {
			assertEquals("login nao pode ser nulo ou vazio", e.getMessage());
		}
	}
	
	@Test
	public void testToString() {
		final String FIM_DE_LINHA = System.lineSeparator();
		try {
			Jogo kof = new Luta("The King of Fighters", 200);
			expert.compraJogo(kof);
			expert.registraJogada("The King of Fighters", 1000, true);
		} 
		catch (Exception e) {
			fail();
		}
		
		String saida = "diviners" + FIM_DE_LINHA
				+ "Savannah - Jogador Veterano" + FIM_DE_LINHA
				+ "Lista de jogos:" + FIM_DE_LINHA
				+ "+ The King of Fighters - LUTA:" + FIM_DE_LINHA
				+ "==> Jogou 1 vez(es)" + FIM_DE_LINHA
				+ "==> Zerou 1 vez(es)" + FIM_DE_LINHA
				+ "==> Maior Score: 1000" + FIM_DE_LINHA + FIM_DE_LINHA
				+ "Total de preco dos jogos: R$ 200.00" + FIM_DE_LINHA;
		assertEquals(saida,expert.toString());
	}
}