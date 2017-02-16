package usuario;

import java.text.DecimalFormat;
import java.util.HashSet;

import jogo.*;
import excecoes.*;

/**
 * 
 * Super classe abstrata
 * 
 * @author Gabriel Fernandes
 *
 */
public abstract class Usuario {
	private String nome;
	private String login;
	private double dinheiro;
	private int x2p;
	private HashSet<Jogo> jogos;

	/**
	 * Construtor da classe usuario
	 * 
	 * @param nome: recebe o nome do usuario
	 * @param login: recebe o login do usuario
	 * @throws MinhaException: caso as entradas sejam invalidas
	 */
	public Usuario(String nome, String login) throws MinhaException {
		Validacao.validaNome(nome);
		Validacao.validaLogin(login);
		
		this.nome = nome;
		this.login = login;
		this.dinheiro = 0.0;
		this.jogos = new HashSet<>();
		this.x2p = 0;
	}
	
	/**
	 * Acessador de nome do usuario
	 * @return o nome do usuario
	 */
	public String getNome() {
		return nome;
	}
	
	/*
	 * Modificador do nome do usuario
	 * 
	 * @throws MinhaException: valida o nome e da erro caso nome nao for valido
	 * 
	 */
	public void setNome(String nome) throws MinhaException {
		
		Validacao.validaNome(nome);
		this.nome = nome;
	}
	
	/**
	 * acessador de login do usuario
	 * @return login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * acessador do dinheiro disponivel do usuario
	 * @return quantidade disponivel pra aquele usuario de dinheiro
	 */
	public double getDinheiro() {
		return dinheiro;
	}
	
	/**
	 * acessador de lista de jogos
	 * @return hashset com os jogos
	 */
	public HashSet<Jogo> getJogos() {
		return jogos;
	}
	
	/**
	 * Adciona dinheiro na conta do usuario
	 * @param dinheiro: dinheiro a ser adcionado
	 * @throws MinhaException: caso dinheiro nao seja valido(abaixo de zero ou zero)
	 */
	public void adicionaDinheiro(double dinheiro) throws MinhaException {
		Validacao.validaDinheiro(dinheiro);
		this.dinheiro += dinheiro;
	}
	
	/**
	 * Oposto ao adcionaDinheiro, usado quando se compra jogos majoritariamente
	 * protected para manter a segurança da da conta do usuario
	 * 
	 * @param quantDinheiro: valor a ser descontado
	 * @throws MinhaException: quando dinheiro nao for valido
	 */
	protected void retiraDinheiro(double quantDinheiro) throws MinhaException {
		Validacao.validaDinheiro(quantDinheiro);
		this.dinheiro -= quantDinheiro;
	}
	
	/**
	 * Usado para operações internas no controle da conta do usuario (por isso o private)
	 * 
	 * @param nomeJogo: jogo a ser pego do hashset
	 * @return o proprio jogo
	 * @throws MinhaException: (na real esse valida nome do jogo aqui nem faz sentido ja q eh pra uso interno da loja,
	 * logo um administrador nao iria colocar um nome invalido para procurar pelo jogo, mas ta ai)
	 */
	private Jogo pegaJogo(String nomeJogo) throws MinhaException {
		Validacao.validaNome(nomeJogo);
		for(Jogo jogo : this.jogos){
			if(nomeJogo.equalsIgnoreCase(jogo.getNome())){
				return jogo;
			}
		}
		throw new ObjInvalido("Jogo nao existe!");
	}
	
	/**
	 * Adciona um novo estilo ao respectivo jogo buscado
	 * 
	 * @param nomeJogo: jogo ao qual se quer adcionar um novo estilo
	 * @param estilo: estilo para ser adcionado ao jogo
	 * @throws MinhaException: casoseja um dado invalido
	 */
	public void adicionaEstilo(String nomeJogo, Estilo estilo) throws MinhaException {
		if(contemJogo(nomeJogo)){
			Jogo jogo = pegaJogo(nomeJogo);
			Validacao.validaStilo(estilo);
			jogo.addEstilo(estilo);
		}
	}
	
	/**
	 * acessador de pontuação x2p
	 * @return os pontos x2p
	 */
	public int getX2p() {
		return x2p;
	}
	
	/**
	 * Para ser implementado em ambas as subclasses
	 * 
	 * @param jogo: jogo a ser comprado
	 * @return boolean confirmando o sucesso da operação
	 * @throws MinhaException: caso algum dado seja errado
	 */
	public abstract boolean compraJogo(Jogo jogo) throws MinhaException;

	/**
	 * Adciona jogo a biblioteca de jogos do respectivo usuario
	 * 
	 * @param jogo: jogo desejado
	 * @return confirmação se foi adcionado com sucesso na forma de true para sim e false para nao
	 * @throws MinhaException: entra em ação caso algum dado seja errado
	 */
	public boolean addJogo(Jogo jogo)throws MinhaException {
		Validacao.validaJogo(jogo);
		return this.jogos.add(jogo);
	}
	
	/**
	 * Busca por jogo
	 * 
	 * @param nomeJogo: o jogo buscado
	 * @return true para contem, false para nao contem
	 * @throws MinhaException: caso nome seja nulo ou vazio
	 */
	public boolean contemJogo(String nomeJogo) throws MinhaException {
		Validacao.validaNome(nomeJogo);
		for(Jogo jogo : this.jogos){
			if(nomeJogo.equalsIgnoreCase(jogo.getNome())){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Usado quando um usuario ganha mais x2p de alguma forma
	 * 
	 * @param x2p: os pontos a serem adcionados
	 * @throws IntInvalido: não podemos adcionar pontos negativos, por isso a validacao para evitar
	 */
	public void aumentaX2p(int x2p) throws IntInvalido {
		Validacao.validaX2p(x2p);
		this.x2p += x2p;
	}
	
	/**
	 * Faz uso do registra jogada de jogo alem de adcionar novas caracteristicas
	 * 
	 * @param nomeJogo: jogo a ser registrado
	 * @param score: para saber se quebrou o recorde
	 * @param zerou: caso tenha-se zerado ele nessa jogatina
	 * @return confirmação da jogada
	 * @throws MinhaException: caso dados sejam errados
	 */
	public boolean registraJogada(String nomeJogo, int score, boolean zerou) throws MinhaException {
		Validacao.validaNome(nomeJogo);
		Validacao.validaScore(score);
		if(contemJogo(nomeJogo)){
			for(Jogo jogo : this.jogos){
				if(nomeJogo.equalsIgnoreCase(jogo.getNome())){
					int x2p = jogo.registraJogada(score, zerou);

					aumentaX2p(x2p);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * util para os calculos do preco dos jogos do usuario
	 * @return o preco total dos jogos do usuario em questao
	 */
	public double calculaPrecoJogos() {
		double totalPreco = 0.0;
		for(Jogo jogo : this.jogos){
			totalPreco += jogo.getPreco();
		}

		return totalPreco;
	}
	
	/**
	 * Sobrescrita do toString
	 */
	@Override
	public String toString() {
		final String FIM_DE_LINHA = System.lineSeparator();
		DecimalFormat dec = new DecimalFormat("0.00");

		String saida = "Lista de jogos:" + FIM_DE_LINHA;

		for(Jogo jogo : this.jogos){

			saida += jogo + FIM_DE_LINHA
					+ "Total de preco dos jogos: R$ " + dec.format(calculaPrecoJogos())
					+ FIM_DE_LINHA;
		}
		return saida;
	}

	/**
	 * Sobrescrita do hashcode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		return result;
	}
	
	/**
	 * Sobrescrita do equals para diferenciar usuarios diferentes auxiliado pelo hashcode
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}
}	
	