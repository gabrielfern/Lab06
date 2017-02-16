package jogo;

import excecoes.IntInvalido;
import excecoes.MinhaException;

/**
 * Molda um jogo RPG
 * 
 * @author Gabriel Fernandes
 *
 */
public class RPG extends Jogo {
	
	/**
	 * Construtor da sublcasse de Jogo Rpg
	 *
	 * 
	 * @param nome Nome do Jogo.
	 * @param preco Preco do Jogo.
	 * @throws MinhaException Caso nome nulo ou vazio e preco for negativo
	 * 
	 */
	public RPG(String nome, int preco) throws MinhaException {
		super(nome, preco);
	}
	
	/**
	 * Sobrescreve o metodo registra jogada da super classe jogo
	 */
	@Override
	public int registraJogada(int score, boolean zerou) throws IntInvalido {
		super.jogou();
		if(zerou) {
			this.zerou();
		}
		
		if(score > super.getMaxScore()) {
			this.setMaxScore(score);
		}
		
		return 10;
	}
	
	/**
	 * 
	 * Sobrescreve o toString padrão e faz uso da implementação geral da classe Jogo para 
	 * aproveitar linahs comuns ás subclasses
	 * 
	 */
	@Override
	public String toString(){
		final String FIM_DE_LINHA = System.lineSeparator();
		String retorno = "+ " + super.getNome() + " - RPG:" + FIM_DE_LINHA
						+ super.toString();
		return retorno;
	}
}