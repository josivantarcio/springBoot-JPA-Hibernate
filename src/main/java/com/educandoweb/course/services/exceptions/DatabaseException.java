package com.educandoweb.course.services.exceptions;

/**
 * Exceção personalizada lançada quando ocorre um erro de integridade no banco de dados.
 * Esta exceção é tipicamente lançada quando uma operação viola restrições de integridade
 * do banco de dados, como chaves estrangeiras ou unicidade.
 */
public class DatabaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constrói uma nova exceção com uma mensagem detalhando o erro de banco de dados.
	 *
	 * @param msg Mensagem detalhando o erro de banco de dados
	 */
	public DatabaseException(String msg) {
		super(msg);
	}
}
