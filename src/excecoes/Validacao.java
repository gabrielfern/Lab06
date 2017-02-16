package excecoes;

import jogo.*;
import usuario.*;

/**
 * Classe auxiliar com metodos estaticos para verificar os tipos de dados recebidos lançando exceções caso seja um tipo nao
 * valido, util para reuso de codigo em todas as classes
 * 
 * @author Gabriel Fernandes
 */
public class Validacao {

	public static void validaNome(String nome) throws NomeInexistente {

		if(nome == null || nome.trim().isEmpty()){
			throw new NomeInexistente("Nome nao pode ser nulo ou vazio");
		}
	}
	
	public static void validaPreco(double preco) throws IntInvalido {

		if(preco <= 0){
			throw new IntInvalido("Preco nao pode ser negativo ou ter valor zero");
		}
	}
	
	public static void validaScore(int score)throws IntInvalido{
		if(score < 0) {
			throw new IntInvalido("Score nao pode ser negativo");
		}
	}
	
	public static void validaMaxScore(int score) throws IntInvalido {
		final int SCOREMAXIMO = 100000;

		if(score > SCOREMAXIMO) {
			throw new IntInvalido("Score maximo atingido");
		}
	}
	
	public static void validaStilo(Estilo estilo)throws MinhaException {
		if(estilo == null){
			throw new MinhaException("Jogabilidade nao pode ser nula");
		}
	}
	
	public static void validaLogin(String login)throws MinhaException {
		if(login == null || login.trim().isEmpty()) {
			throw new MinhaException("login nao pode ser nulo ou vazio");
		}
	}
	
	public static void validaDinheiro(double dinheiro) throws IntInvalido {

		if(dinheiro <= 0){
			throw new IntInvalido("Quantidade de dinheiro nao pode ser negativa ou zero"); 
		}
	}
	
	public static void validaJogo(Jogo jogo) throws MinhaException {
		if(jogo == null){
			throw new MinhaException("Jogo nao pode ser nulo");
		}
	}
	
	public static void validaX2p(int x2p) throws IntInvalido {
		if(x2p < 0) {
			throw new IntInvalido("X2p invalido!");
		}
	}

	public static void validaVeterano(Usuario usuario) throws MinhaException {
		if(usuario == null) {
			throw new ObjInvalido("Usuario nao existe");
		}
		
		if(usuario instanceof Veterano) {
			throw new MinhaException("Usuario ja eh veterano");
		}
	}
}