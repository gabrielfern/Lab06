package jogoTest;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import excecoes.MinhaException;
import jogo.Estilo;
import jogo.Plataforma;

/**
 * Alguns testes simples e gerais para mostrar o funcionamento correto das classes que herdam de jogo,
 * evitando excesso de testes desnecessários
 * 
 * @author Gabriel Fernandes
 */
public class PlataformaTest {
	Plataforma mario;

	@Before
	public void setUp() throws MinhaException {
		mario = new Plataforma("Mario Land", 30);
	}
	@Test
	public void testExceptions() throws MinhaException {
		try {
			new Plataforma(null, 100);
			Assert.fail();
		}
		catch (Exception e) {
			assertEquals("Nome nao pode ser nulo ou vazio", e.getMessage());
		}
		
		try {
			new Plataforma("BlueSky", -45);
			Assert.fail();
		}
		catch (Exception e) {
			assertEquals("Preco nao pode ser negativo ou ter valor zero", e.getMessage());
		}
	}
	
	@Test
	public void testZeradas() {
		assertEquals(0, mario.getZeradas());
		mario.zerou();
		assertEquals(1, mario.getZeradas());
		mario.zerou();
		assertEquals(2, mario.getZeradas());
	}
	
	@Test
	public void testJogou() {
		assertEquals(0, mario.getJogadas());
		mario.jogou();
		assertEquals(1, mario.getJogadas());
		mario.jogou();
		assertEquals(2, mario.getJogadas());
	}
	
	@Test
	public void testSetMaxScoreScore() throws MinhaException {
		mario.setMaxScore(456);
		assertEquals(456,mario.getMaxScore());
		try {
			mario.setMaxScore(-45);
			Assert.fail();
		}
		catch(Exception e) {
			assertEquals("Score nao pode ser negativo", e.getMessage());
		}
	}
	
	@Test
	public void testRegistraJogada() throws Exception{
		assertEquals(20, mario.registraJogada(150000, true));
		assertEquals(0, mario.registraJogada(45000, false));
		assertEquals(20, mario.registraJogada(250000, true));
		assertEquals(3, mario.getJogadas());
	}
	
	@Test
	public void testAddEstilo() throws MinhaException {
		
		assertTrue(mario.addEstilo(Estilo.ONLINE));
		assertTrue(mario.addEstilo(Estilo.MULTIPLAYER));
		
		HashSet<Estilo> estilos = mario.getEstilo();
		for(Estilo e : estilos) {
			assertTrue(e == Estilo.ONLINE || e == Estilo.MULTIPLAYER);
		}
	}

	@Test
	public void testToString() throws MinhaException {
		final String FIM_DE_LINHA = System.lineSeparator();
	
		String saida = "+ Mario Land - PLATAFORMA:" + FIM_DE_LINHA 
						+ "==> Jogou 0 vez(es)" + FIM_DE_LINHA
						+ "==> Zerou 0 vez(es)" + FIM_DE_LINHA
						+ "==> Maior Score: 0" + FIM_DE_LINHA;
		
		assertEquals(saida, mario.toString());
	}
}