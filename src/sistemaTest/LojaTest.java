package sistemaTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import jogo.Estilo;
import sistema.Loja;

/**
 * Nestes testes do sistema(unicamente loja infelizmente) temos algumas impressões no console,
 * pois como pedia o lab era obrigação da loja a captura de exceções e impressão de dados, 
 * portanto, deixo aqui alguns dados sendo imprimidos ao se executar o teste
 * 
 * @author Gabriel Fernandes
 */
public class LojaTest {
	private Loja lolja;

	@Before
	public void Loja() {
		lolja = new Loja();
		lolja.addUsuario("Hyejeong", "dongdong810", "Veterano");
		lolja.addDinheioUsuario("dongdong810", 450);
	}
	
	@Test
	public void testAddUsuario() {
		assertTrue(lolja.addUsuario("Seolhyun", "sh_9513","Veterano"));
		assertTrue(lolja.addUsuario("Choa", "queenchoa_","Noob"));
	}
	
	@Test
	public void testAddDinheioUsuario() {
		assertTrue(lolja.addDinheioUsuario("dongdong810", 2500.25));
	}
	
	@Test
	public void testContemUsuario() {
		assertTrue(lolja.contemUsuario("dongdong810"));
		assertFalse(lolja.contemUsuario("sh_9513"));
	}
	
	@Test
	public void testRegistraJogada() {
		assertTrue(lolja.vendeJogo("dongdong810", "The Sims", 100, "RPG"));
		
		assertTrue(lolja.registraJogada("dongdong810", "The Sims", 20000, true));
		assertFalse(lolja.registraJogada("queenchoa_", "The Sims", 20000, true));
	}
	
	@Test
	public void testAddEstilo() {
		assertTrue(lolja.vendeJogo("dongdong810", "The Sims", 100, "RPG"));
		assertTrue(lolja.addEstilo("dongdong810", "The Sims", Estilo.ONLINE));
		assertFalse(lolja.addEstilo("sh_9513", "The Sims", Estilo.ONLINE));
	}

	@Test
	public void testUpgrade() {
		assertTrue(lolja.addUsuario("박초롱", "mulgokizary", "Noob"));
		
		assertTrue(lolja.addDinheioUsuario("mulgokizary", 2000));
		
		assertTrue(lolja.vendeJogo("mulgokizary", "Super Smash Bros", 555, "Luta"));
		
		assertTrue(lolja.registraJogada("mulgokizary", "Super Smash Bros", 20000, true));
		
		System.out.println(lolja); // Note no console a impressão para o usuário antes do upgrade
		
		assertTrue(lolja.upgrade("mulgokizary"));
		
		System.out.println(lolja); // Perceba que ele conserva os jogos que possuia antes :D
		
		assertFalse(lolja.upgrade("dongdong810"));
	}
	
	@Test
	public void testToString() {
		lolja.vendeJogo("dongdong810", "The Sims", 100, "RPG");
		lolja.registraJogada("dongdong810", "The Sims", 20000, true);
		
		final String FIM_DE_LINHA = System.lineSeparator();
		
		String saida = "=== Central P2-CG ===" + FIM_DE_LINHA + FIM_DE_LINHA
							+ "dongdong810" + FIM_DE_LINHA
							+"Hyejeong - Jogador Veterano" + FIM_DE_LINHA
							+"Lista de jogos:" + FIM_DE_LINHA
							+"+ The Sims - RPG:" + FIM_DE_LINHA
							+"==> Jogou 1 vez(es)" + FIM_DE_LINHA
							+"==> Zerou 1 vez(es)" + FIM_DE_LINHA
							+"==> Maior Score: 20000" + FIM_DE_LINHA + FIM_DE_LINHA
							+"Total de preco dos jogos: R$ 100.00" + FIM_DE_LINHA + FIM_DE_LINHA
							+ "-------------------------------------------------------" + FIM_DE_LINHA;
		
		assertEquals(saida, lolja.toString());
	}
}