package jogoTest;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import excecoes.MinhaException;
import jogo.Estilo;
import jogo.Luta;

/**
 * Alguns testes simples e gerais para mostrar o funcionamento correto das classes que herdam de jogo,
 * evitando excesso de testes desnecessários
 * 
 * @author Gabriel Fernandes
 */
public class LutaTest {
	Luta mortalKombat;

	@Before
	public void setUp() throws MinhaException {
		mortalKombat = new Luta("Mortal Kombat", 30);
	}
	@Test
	public void testExceptions() throws MinhaException {
		try {
			new Luta(null, 100);
			Assert.fail();
		}
		catch (Exception e) {
			assertEquals("Nome nao pode ser nulo ou vazio", e.getMessage());
		}
		
		try {
			new Luta("BlueSky", -45);
			Assert.fail();
		}
		catch (Exception e) {
			assertEquals("Preco nao pode ser negativo ou ter valor zero", e.getMessage());
		}
	}
	
	@Test
	public void testZeradas() {
		assertEquals(0, mortalKombat.getZeradas());
		mortalKombat.zerou();
		assertEquals(1, mortalKombat.getZeradas());
		mortalKombat.zerou();
		assertEquals(2, mortalKombat.getZeradas());
	}
	
	@Test
	public void testJogou() {
		assertEquals(0, mortalKombat.getJogadas());
		mortalKombat.jogou();
		assertEquals(1, mortalKombat.getJogadas());
		mortalKombat.jogou();
		assertEquals(2, mortalKombat.getJogadas());
	}
	
	@Test
	public void testSetMaxScoreScore() throws MinhaException {
		mortalKombat.setMaxScore(456);
		assertEquals(456,mortalKombat.getMaxScore());
		try {
			mortalKombat.setMaxScore(-45);
			Assert.fail();
		}
		catch(Exception e) {
			assertEquals("Score nao pode ser negativo", e.getMessage());
		}
	}
	
	@Test
	public void testRegistraJogada() throws Exception{
		assertEquals(100, mortalKombat.registraJogada(100000, true));
		assertEquals(1, mortalKombat.getZeradas());
		try {
			assertEquals(100, mortalKombat.registraJogada(100001, false));
		}
		catch (MinhaException e) {
			assertEquals("Score maximo de 100.000 ultrapassado", e.getMessage());
		}
		assertEquals(0, mortalKombat.registraJogada(85000, false));
		assertEquals(3, mortalKombat.getJogadas());
	}
	
	@Test
	public void testAddEstilo() throws MinhaException {
		
		assertTrue(mortalKombat.addEstilo(Estilo.ONLINE));
		assertTrue(mortalKombat.addEstilo(Estilo.MULTIPLAYER));
		
		HashSet<Estilo> estilos = mortalKombat.getEstilo();
		for(Estilo e : estilos) {
			assertTrue(e == Estilo.ONLINE || e == Estilo.MULTIPLAYER);
		}
	}
	
	@Test
	public void testToString() {

		final String FIM_DE_LINHA = System.lineSeparator();
		
		String saida = "+ Mortal Kombat - LUTA:" + FIM_DE_LINHA 
							+ "==> Jogou 0 vez(es)" + FIM_DE_LINHA
							+ "==> Zerou 0 vez(es)" + FIM_DE_LINHA
							+ "==> Maior Score: 0" + FIM_DE_LINHA;
		
		assertEquals(saida, mortalKombat.toString());
	}
}