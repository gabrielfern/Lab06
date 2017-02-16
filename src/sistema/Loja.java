package sistema;

import java.util.HashSet;
import excecoes.*;
import jogo.*;
import usuario.*;

/**
 * Classe responsavel por criar usuarios e trabalhar em cima de suas contas
 * 
 * @author Gabriel Fernandes
 *
 */
public class Loja {
	private HashSet<Usuario> usuarios;
	
	/**
	 * Contrutor simples, nao recebe nada como parametro, ao ser instanciada 
	 * ja cria um set de usuarios
	 */
	public Loja() { 
		this.usuarios = new HashSet<Usuario>();
	}
	
	/**
	 * Metodo interno da loja para atribuir um jogo ao usuario(por isso do private)
	 * 
	 * @param nomeJogo: nome dado ao novo jogo
	 * @param preco: preco do novo jogo
	 * @param tipo: se eh de rpg luta ou plataforma o seu novo jogo
	 * @return
	 */
	private Jogo criaJogo(String nomeJogo, int preco, String tipo) {

		switch(tipo){
		case "RPG":
			Jogo novoRPG = criaRPG(nomeJogo, preco);
			return novoRPG;

		case "Plataforma":
			Jogo novoPlataforma = criaPlataforma(nomeJogo, preco);
			return novoPlataforma;

		case "Luta":
			Jogo novoLuta = criaLuta(nomeJogo, preco);
			return novoLuta;
		default:
			return null;
		}
	}
	
	/**
	 * Usado como suporte para criar o tipo de jogo desejado
	 * 
	 * @param nome: nome do jogo
	 * @param preco: preco do jogo
	 * @return retorna o proprio jogo caso tenha sucesso, ou null caso receba uma exceção ao criar o jogo
	 * (caso o nome do jogo seja vazio por exemplo)
	 */
	private Jogo criaRPG(String nome, int preco) {
		try {
			Jogo jogo = new RPG(nome, preco);
			return jogo;

		} catch (MinhaException e) {
			System.out.println("Dados são inválidos");
			return null;
		}
	}
	
	/**
	 * Usado como suporte para criar o tipo de jogo desejado
	 * 
	 * @param nome: nome do jogo
	 * @param preco: preco do jogo
	 * @return retorna o proprio jogo caso tenha sucesso, ou null caso receba uma exceção ao criar o jogo
	 * (caso o nome do jogo seja vazio por exemplo)
	 */
	private Jogo criaLuta(String nome, int preco) {
		try {
			Jogo jogo = new Luta(nome, preco);
			return jogo;

		} catch (MinhaException e) {
			System.out.println("Dados são inválidos");
			return null;
		}
	}
	
	/**
	 * Usado como suporte para criar o tipo de jogo desejado
	 * 
	 * @param nome: nome do jogo
	 * @param preco: preco do jogo
	 * @return retorna o proprio jogo caso tenha sucesso, ou null caso receba uma exceção ao criar o jogo
	 * (caso o nome do jogo seja vazio por exemplo)
	 */
	private Jogo criaPlataforma(String nome, int preco) {
		try {
			Jogo jogo = new Plataforma(nome, preco);
			return jogo;

		} catch (MinhaException e) {
			System.out.println("Dados são inválidos");
			return null;
		}
	}
	
	/**
	 * Funcionalidade especifica da loja de adcionar um usuario, adciona ao seu set de usuarios
	 * 
	 * @param nome: nome do novo usuario
	 * @param login: login para o novo usuario
	 * @param ehNoob: indicador se o novo usuario eh noob ou ja eh veterano(solução um tanto quanto deselegante, mas funcional)
	 * @return confirmação da tentativa de adcionar o novo usuario
	 */
	public boolean addUsuario(String nome, String login, String ehNoob) {
		try {
			switch(ehNoob) {
				case "Noob":
					if(!contemUsuario(nome)){
						Usuario novoNoob = new Noob(nome, login);
						usuarios.add(novoNoob);
						
						return true;
					}
			return false;

			case "Veterano":
				if(!contemUsuario(nome)) {
					Usuario novoVeterano = new Veterano(nome, login);
					usuarios.add(novoVeterano);

					return true;
				}
			return false;
			}
		}
		catch (MinhaException e) {
			System.out.println("Nome, login invalido ou tipo de usuario nao existe!");
		}
		return true;
	}
	
	/**
	 * Realiza a venda de jogos a um usuario ja cadastrado na loja
	 * 
	 * @param login: login do usuario para buscar ele nos registros
	 * @param nomeJogo: nome do jogo a ser vendido
	 * @param precoJogo: preco referente ao jogo
	 * @param tipoJogo: tipo do jogo
	 * @return boolean de confirmação
	 */
	public boolean vendeJogo(String login, String nomeJogo, int precoJogo, String tipoJogo) {
			try {
				Validacao.validaLogin(login);
				
				if(contemUsuario(login)) {
					Usuario usuario = pegaUsuario(login);
					Jogo novoJogo = criaJogo(nomeJogo, precoJogo, tipoJogo);
					return usuario.compraJogo(novoJogo);
				}
				else {
					throw new NomeInexistente("Usuário inexistente");
				}	
			}
				catch (MinhaException e) {
				System.out.println("Dados inválidos!");
			}
		return false;
	}

	/**
	 * para uso interno da loja
	 * 
	 * @param login: login do usuario para acessar ele
	 * @return o usuario desejado para realizar operações com ele(solução bem util inclusive)
	 */
	private Usuario pegaUsuario(String login) {
	try {
		for(Usuario usuario : this.usuarios) {
			if(usuario.getLogin().equals(login)){
				return usuario;
			}
			else {
				throw new NomeInexistente("Tipo de jogo inexistente!");
			}
		}
	}
		catch (MinhaException e) {
		System.out.println("Usuário não encontrado");
		}
		return null;
	}
	
	/**
	 * Busca por usuario
	 * 
	 * @param outroUsuario: usuario ao qual se quer saber se esta nos registros da loja(apesar do nome "outro usuario")
	 * @return confirmação se ele esta presente na loja
	 */
	public boolean contemUsuario(String outroUsuario) {

		for(Usuario usuario : this.usuarios) {
			if(usuario.getLogin().equals(outroUsuario)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Analogo ao adciona estilo da classe usuario
	 * 
	 * @param login: login do usuario na qual se deseja adcionar um estilo a um jogo pertecente a ele
	 * @param nomeJogo: jogo em questao
	 * @param estilo: estilo desejado
	 * @return boolean de confirmação
	 */
	public boolean addEstilo(String login, String nomeJogo, Estilo estilo) {

		if(contemUsuario(login)) {
			try{
				Usuario usuario = pegaUsuario(login);

				usuario.adicionaEstilo(nomeJogo, estilo);
				return true;

			}
			catch(MinhaException e) {
				System.out.println("Usuario ou jogo nao existe");
				return false;
			}
		}else{
			return false;
		}
	}
	
	/**
	 * Faz uso do registraJogada de usuario para realizar uma jogada com algum dos usuarios ja presentes na loja
	 * 
	 * @param login: login para identificar usuario
	 * @param nomeJogo: jogo desejado
	 * @param score: pontuação na jogada
	 * @param zerou: indicativo se zerou ou nao na dada jogada
	 * @return boolean de confirmacao
	 */
	public boolean registraJogada(String login, String nomeJogo, int score, boolean zerou) {

		try{
			Validacao.validaLogin(login);

			if(contemUsuario(login)) {
				Usuario usuario = pegaUsuario(login);

				return usuario.registraJogada(nomeJogo, score, zerou);

			}else{
				return false;
			}

		} catch(MinhaException e) {
			System.out.println("Login inválido!");
			return false;
		}
	}
	
	/**
	 * Para se adcionar recursos na conta do usuario
	 * 
	 * @param login: para se buscar o usuario
	 * @param quantDinheiro: dinheiro a adcionar
	 * @return boolean de confirmaçao
	 */
	public boolean addDinheioUsuario(String login,double quantDinheiro) {
		try {
			Validacao.validaLogin(login);
			Validacao.validaDinheiro(quantDinheiro);
			
			if(contemUsuario(login)){
				Usuario usuario = pegaUsuario(login);
				usuario.adicionaDinheiro(quantDinheiro);
				return true;

			}
			else{
				return false;
			}
		} catch (MinhaException e) {
			System.out.println("Login ou quantia de dinheiro invalido");
			return false;
		}
	}
	
	/**
	 * Metodo que implementa o upgrades dos usuarios quando estes dispom dos requisitos
	 * 
	 * @param login: necessario para encontrar o usuario
	 * @return boolean de confirmação
	 */
	public boolean upgrade(String login) {

		final int X2P_MINIMO = 1000;

		try {
			if(contemUsuario(login)){
				Usuario usuario = pegaUsuario(login);
				Validacao.validaVeterano(usuario);
				
				if(usuario.getX2p() >= X2P_MINIMO){
					Usuario veterano = new Veterano(usuario.getNome(), usuario.getLogin());
					for(Jogo j : usuario.getJogos()) {
						veterano.addJogo(j);
					}
					usuarios.add(veterano);
					usuarios.remove(usuario);
					return true;
				}
			}
		} 
		catch (MinhaException e) {
			System.out.println("Login fornecido eh invalido, usuario eh veterano ou nao possue x2p suficiente!");
			return false;
		}
		return false;
	}

	/**
	 * Sobrescrita ao toString, retorna uma string como os usuarios cadastrados na loja atualmente
	 */
	@Override
	public String toString(){
		final String FIM_DE_LINHA = System.lineSeparator();
		String retorno = "=== Central P2-CG ===" + FIM_DE_LINHA + FIM_DE_LINHA;

		for(Usuario usuario : this.usuarios){

			retorno += usuario + FIM_DE_LINHA
					+ "-------------------------------------------------------" + FIM_DE_LINHA;
		}

		return retorno;
	}

	/**
	 * Implementação do hashcode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((usuarios == null) ? 0 : usuarios.hashCode());
		return result;
	}

	/**
	 * Implementação do equals
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Loja other = (Loja) obj;
		if (usuarios == null) {
			if (other.usuarios != null)
				return false;
		} else if (!usuarios.equals(other.usuarios))
			return false;
		return true;
	}
}
