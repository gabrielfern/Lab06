package excecoes;

/**
 * Classe meramente para gerar uma exceção quando necessario
 * (faz uso do validação para comprovar o dado invalido e aqui lança-se a exceção)
 * 
 * @author Gabriel Fernandes
 */
public class IntInvalido extends MinhaException {

	private static final long serialVersionUID = 1L;

	public IntInvalido() {
		super("valor passado invalido");
	}
	
	public IntInvalido(String mensagem) {
		super(mensagem);
	}
}