package com.educandoweb.course.services.exceptions;

/**
 * Exceção personalizada lançada quando um recurso não é encontrado no sistema.
 * Esta exceção é tipicamente lançada quando uma operação tenta acessar um recurso
 * que não existe no banco de dados.
 */
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constrói uma nova exceção com uma mensagem detalhando o ID do recurso não encontrado.
	 *
	 * @param id ID do recurso que não foi encontrado
	 */
	public ResourceNotFoundException(Object id) {
		super("Resource not found. Id " + id);
	}
}
