package jogo;

import java.util.HashSet;
import excecoes.*;

/**
 * Superclasse abstrata responsável por moldar uma forma comum
 * para os jogos que a herdam
 * 
 *  @author Gabriel Fernandes
 */

public abstract class Jogo {
	private String nome;
	private double preco;
	private int maxScore;
	private int jogadas;
	private int zeradas;
	private HashSet<Estilo> estilos;
	
	/**
	 * Construtor da classe
	 *
	 * 
	 * @param nome: Nome do Jogo.
	 * @param preco: Preco do Jogo.
	 * @throws MinhaException: Caso nome nulo ou vazio e preco for negativo
	 * 
	 */
	public Jogo(String nome, int preco) throws MinhaException {
		Validacao.validaNome(nome);
		Validacao.validaPreco(preco);
		
		this.nome = nome;
		this.preco = preco;
		this.maxScore = 0;
		this.jogadas = 0;
		this.zeradas = 0;
		this.estilos = new HashSet<>();
	}
	
	/**
	 * acessador de nome
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * acessador de preço
	 */
	public double getPreco() {
		return preco;
	}
	
	/**
	 * modificador de preço
	 */
	public void setPreco(double preco) throws IntInvalido {
		Validacao.validaPreco(preco);
		this.preco = preco;
	}

	/**
	 * acessador de score máximo realizado até o momento
	 */
	public int getMaxScore() {
		return maxScore;
	}
	
	/**
	 * modificador do maior score atingido pelo usuário
	 */
	public void setMaxScore(int maxScore) throws IntInvalido {
		Validacao.validaScore(maxScore);
		this.maxScore = maxScore;
	}

	/**
	 * acessador de quantidade de vezes jogadas até o momento
	 */
	public int getJogadas() {
		return jogadas;
	}

	/**
	 * acessador de quantidade de vezes zeradas até o momento
	 */
	public int getZeradas() {
		return zeradas;
	}
	
	/**
	 * Metodo usado para incrementar as vezes jogadas do respectivo jogo
	 */
	public void jogou() {
		this.jogadas++;
	}
	
	/**
	 * Metodo usado para incrementar as vezes zeradas do respectivo jogo
	 */
	public void zerou() {
		this.zeradas ++;
	}
	
	/**
	 * @param estilo: estilo novo para adcionar ao jogo
	 * @return true para estilo de jogo adicionado com sucesso, false para falha ao adcionar
	 */
	public boolean addEstilo(Estilo estilo) throws MinhaException {
		Validacao.validaStilo(estilo);
		return this.estilos.add(estilo);
	}
	
	public HashSet<Estilo> getEstilo() {
		return this.estilos;
	}
	
	/**
	 * 
	 * @param score: pontuação atingida pelo usuário
	 * @param zerou: true para se ele zerou, false para se ele não zerou
	 * @return pontos x2p conseguidos com a jogada
	 * @throws IntInvalido: Faz a verificação se é um inteiro válido, se não gera exceção
	 */
	public abstract int registraJogada(int score, boolean zerou) throws IntInvalido;

	/**
	 * Sobrescrita do toString
	 */
	@Override
	public String toString() {
		final String FIM_DE_LINHA = System.lineSeparator();
		
		String retorno = "==> Jogou " + getJogadas() + " vez(es)" + FIM_DE_LINHA
					 + "==> Zerou " + getZeradas() + " vez(es)" + FIM_DE_LINHA
					 + "==> Maior Score: " + getMaxScore() + FIM_DE_LINHA;

		return retorno;
	}
	
	/**
	 * Metodo hash para comparar dois jogos
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		long temp;
		temp = Double.doubleToLongBits(preco);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	
	/**
	 * Metodo equals para comparar dois jogos
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jogo other = (Jogo) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (Double.doubleToLongBits(preco) != Double.doubleToLongBits(other.preco))
			return false;
		return true;
	}
}
