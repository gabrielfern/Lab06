package excecoes;

/**
 * Classe meramente para gerar uma exceção quando necessario
 * (faz uso do validação para comprovar o dado invalido e aqui lança-se a exceção)
 * 
 * @author Gabriel Fernandes
 */
public class ObjInvalido extends MinhaException {
	
	private static final long serialVersionUID = 1L;

	public ObjInvalido() {
		super("O objeto passado nao pode ser nulo.");
	}
		
	public ObjInvalido(String mensagem) {
		super(mensagem);
	}
}
