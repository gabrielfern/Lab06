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
import usuario.Noob;

/**
 * Testes bem gerais que certifica que ambos tipos de usuários noob e veterano estão funcionando de acordo
 * com o esperado, acredito que cobrem as partes mais interessantes de ambas as implementações
 * 
 * @author Gabriel Fernandes
 */
public class NoobTest {
	private Noob newbie;
	
	@Before
	public void setUp() throws MinhaException {
		newbie = new Noob("Savannah", "diviners");
		newbie.adicionaDinheiro(350);
	}
	
	@Test
	public void testGetLogin() {
		assertEquals("diviners", newbie.getLogin());
	}

	@Test
	public void testGetDinheiro() {
		assertTrue(350 == newbie.getDinheiro());
	}
	
	@Test
	public void testDepositaDinheiro() throws MinhaException {

		newbie.adicionaDinheiro(350);
		assertTrue(700 == newbie.getDinheiro());
		
		try {
			newbie.adicionaDinheiro(-500);
			fail();
		}
		catch (Exception e) {
			assertEquals("Quantidade de dinheiro nao pode ser negativa ou zero", e.getMessage());
		}
		
		assertTrue(700 == newbie.getDinheiro());
	}

	@Test
	public void testAdicionaX2p() {
		try {
			assertEquals(0, newbie.getX2p());
			newbie.aumentaX2p(500);
		}
		catch (Exception e) {
			assertEquals("valor passado invalido", e.getMessage());
		}

		try {
			newbie.aumentaX2p(-500);
			fail();
		}
		catch (Exception e) {
			assertEquals("X2p invalido!", e.getMessage());
		}
	}
	
	@Test
	public void testCompraJogo() throws MinhaException {
		RPG noMansSky = new RPG("No Man's Sky", 150);
		assertTrue(newbie.compraJogo(noMansSky));
		assertTrue(215.0 == newbie.getDinheiro());
		assertTrue(newbie.contemJogo("No Man's Sky"));
		Plataforma failGame = null;
		try {
			newbie.compraJogo(failGame);
			Assert.fail();
		}
		catch (Exception e) {
			assertEquals("Jogo nao pode ser nulo", e.getMessage());
		}
	}
	
	@Test
	public void testNoob() throws MinhaException {
		
		try {
			new Noob(null, "teste");
			fail();
		}catch(Exception e) {
			assertEquals("Nome nao pode ser nulo ou vazio", e.getMessage());
		}
		
		try {
			new Noob("", "teste");
			fail();
		}catch(Exception e) {
			assertEquals("Nome nao pode ser nulo ou vazio", e.getMessage());
		}
		
		try {
			new Noob("teste", null);
			fail();
		}catch(Exception e) {
			assertEquals("login nao pode ser nulo ou vazio", e.getMessage());
		}
		
		try {
			new Noob("teste", "");
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
			newbie.compraJogo(kof);
			newbie.registraJogada("The King of Fighters", 1000, true);
		} 
		catch (Exception e) {
			fail();
		}
		
		String saida = "diviners" + FIM_DE_LINHA
				+ "Savannah - Jogador Noob" + FIM_DE_LINHA
				+ "Lista de jogos:" + FIM_DE_LINHA
				+ "+ The King of Fighters - LUTA:" + FIM_DE_LINHA
				+ "==> Jogou 1 vez(es)" + FIM_DE_LINHA
				+ "==> Zerou 1 vez(es)" + FIM_DE_LINHA
				+ "==> Maior Score: 1000" + FIM_DE_LINHA + FIM_DE_LINHA
				+ "Total de preco dos jogos: R$ 200.00" + FIM_DE_LINHA;
		assertEquals(saida,newbie.toString());
	}
}