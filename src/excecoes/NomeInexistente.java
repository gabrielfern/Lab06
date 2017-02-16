package excecoes;

/**
 * Classe meramente para gerar uma exceção quando necessario
 * (faz uso do validação para comprovar o dado invalido e aqui lança-se a exceção)
 * 
 * @author Gabriel Fernandes
 */
public class NomeInexistente extends MinhaException {

	private static final long serialVersionUID = 1L;

	public NomeInexistente() {
		super("O parametro passado nao pode ser nulo ou vazio");
	}
	
	public NomeInexistente(String msg) {
		super(msg);
	}
}