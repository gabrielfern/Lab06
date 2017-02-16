package jogoTest;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import excecoes.MinhaException;
import jogo.Estilo;
import jogo.RPG;

/**
 * Alguns testes simples e gerais para mostrar o funcionamento correto das classes que herdam de jogo,
 * evitando excesso de testes desnecessários
 * 
 * @author Gabriel Fernandes
 */
public class RPGTest {
	RPG narutoGame;

	@Before
	public void setUp() throws MinhaException {
		narutoGame = new RPG("Naruto Game", 30);
	}
	@Test
	public void testExceptions() throws MinhaException {
		try {
			new RPG(null, 100);
			Assert.fail();
		}
		catch (Exception e) {
			assertEquals("Nome nao pode ser nulo ou vazio", e.getMessage());
		}
		
		try {
			new RPG("BlueSky", -45);
			Assert.fail();
		}
		catch (Exception e) {
			assertEquals("Preco nao pode ser negativo ou ter valor zero", e.getMessage());
		}
	}
	
	@Test
	public void testZeradas() {
		assertEquals(0, narutoGame.getZeradas());
		narutoGame.zerou();
		assertEquals(1, narutoGame.getZeradas());
		narutoGame.zerou();
		assertEquals(2, narutoGame.getZeradas());
	}
	
	@Test
	public void testJogou() {
		assertEquals(0, narutoGame.getJogadas());
		narutoGame.jogou();
		assertEquals(1, narutoGame.getJogadas());
		narutoGame.jogou();
		assertEquals(2, narutoGame.getJogadas());
	}
	
	@Test
	public void testSetMaxScoreScore() throws MinhaException {
		narutoGame.setMaxScore(456);
		assertEquals(456,narutoGame.getMaxScore());
		try {
			narutoGame.setMaxScore(-45);
			Assert.fail();
		}
		catch(Exception e) {
			assertEquals("Score nao pode ser negativo", e.getMessage());
		}
	}
	
	@Test
	public void testRegistraJogada() throws MinhaException {
		assertEquals(10, narutoGame.registraJogada(700, true));
		assertEquals(10, narutoGame.registraJogada(2000, false));
		assertEquals(1, narutoGame.getZeradas());
		assertEquals(2000, narutoGame.getMaxScore());
		assertEquals(2, narutoGame.getJogadas());
	}
	
	@Test
	public void testAddEstilo() throws MinhaException {
		
		assertTrue(narutoGame.addEstilo(Estilo.ONLINE));
		assertTrue(narutoGame.addEstilo(Estilo.MULTIPLAYER));
		
		HashSet<Estilo> estilos = narutoGame.getEstilo();
		for(Estilo e : estilos) {
			assertTrue(e == Estilo.ONLINE || e == Estilo.MULTIPLAYER);
		}
	}
	
	@Test
	public void testToString() {

		final String FIM_DE_LINHA = System.lineSeparator();

		String saida = "+ Naruto Game - RPG:" + FIM_DE_LINHA 
				+ "==> Jogou 0 vez(es)" + FIM_DE_LINHA
				+ "==> Zerou 0 vez(es)" + FIM_DE_LINHA
				+ "==> Maior Score: 0" + FIM_DE_LINHA;

		assertEquals(saida, narutoGame.toString());
	}
}
