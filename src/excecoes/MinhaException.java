package excecoes;

/**
 * Implementação de uma classe comum para minhas exceções,
 * para ao jogar(throws) uma exceção num metodo que tem mais de um tipo de exceção testada
 * possibilitar por meio de herança usar um único throw para varias exceções diferentes
 * 
 * @author Gabriel Fernandes da Silva
 */

public class MinhaException extends Exception{

	private static final long serialVersionUID = 1L;

	public MinhaException(){
		super("Logica errada ou dados invalidos!");
	}
	
	public MinhaException(String mensagem){
		super(mensagem);
	}
}
