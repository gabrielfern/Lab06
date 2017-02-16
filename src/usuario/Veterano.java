package usuario;

import excecoes.MinhaException;
import excecoes.Validacao;
import jogo.Jogo;

/**
 * 
 * Sub classe de usuario
 * 
 * @author Gabriel Fernandes
 *
 */
public class Veterano extends Usuario {
	
	/**
	 * Contrutor da classe Veterano
	 * 
	 * @param nome: recebe o nome do usuario
	 * @param login: recebe o login do usuario
	 * @throws MinhaException: caso as entradas sejam invalidas
	 */
	public Veterano(String nome, String login) throws MinhaException {
		super(nome, login);
	}
	
	/**
	 * Sobrescreve o compra jogo para se adequar as diferenças entre os tipos noob e veterano
	 */
	@Override
	public boolean compraJogo(Jogo jogoAComprar) throws MinhaException {
		Validacao.validaJogo(jogoAComprar);
		
		if(super.getDinheiro() >= (jogoAComprar.getPreco() * 0.8)) {
			super.retiraDinheiro(jogoAComprar.getPreco() * 0.8);
			Double temp = 15 * jogoAComprar.getPreco();
			super.aumentaX2p(temp.intValue());
			return super.addJogo(jogoAComprar);
		}
		else {
			return false;
		}
	}
	
	/**
	 * Sobrescrita parcial do toString da super classe usuario
	 * (parcial pois usa parte do toString da superclass)
	 * 
	 */
	@Override
	public String toString() {
		
		final String FIM_DE_LINHA = System.lineSeparator();
		String retorno = super.getLogin() + FIM_DE_LINHA
				       + super.getNome() + " - Jogador Veterano" + FIM_DE_LINHA
				       + super.toString();
		return retorno;
	}
}
