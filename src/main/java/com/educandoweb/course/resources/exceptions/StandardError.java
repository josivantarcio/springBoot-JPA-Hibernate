package com.educandoweb.course.resources.exceptions;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Classe que representa um erro padrão retornado pela API.
 * Contém informações detalhadas sobre o erro ocorrido, incluindo timestamp,
 * status HTTP, tipo do erro, mensagem e caminho da requisição.
 */
public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")	
	private Instant timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;

	/**
	 * Construtor padrão sem argumentos.
	 */
	public StandardError() {
	}

	/**
	 * Constrói um novo StandardError com todos os campos necessários.
	 *
	 * @param timestamp Momento em que o erro ocorreu
	 * @param status Código de status HTTP
	 * @param error Tipo do erro
	 * @param message Mensagem detalhada do erro
	 * @param path Caminho da requisição que causou o erro
	 */
	public StandardError(Instant timestamp, Integer status, String error, String message, String path) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}

	/**
	 * Retorna o momento em que o erro ocorreu.
	 *
	 * @return Timestamp do erro
	 */
	public Instant getTimestamp() {
		return timestamp;
	}

	/**
	 * Define o momento em que o erro ocorreu.
	 *
	 * @param timestamp Timestamp do erro
	 */
	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * Retorna o código de status HTTP.
	 *
	 * @return Código de status HTTP
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * Define o código de status HTTP.
	 *
	 * @param status Código de status HTTP
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * Retorna o tipo do erro.
	 *
	 * @return Tipo do erro
	 */
	public String getError() {
		return error;
	}

	/**
	 * Define o tipo do erro.
	 *
	 * @param error Tipo do erro
	 */
	public void setError(String error) {
		this.error = error;
	}

	/**
	 * Retorna a mensagem detalhada do erro.
	 *
	 * @return Mensagem do erro
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Define a mensagem detalhada do erro.
	 *
	 * @param message Mensagem do erro
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Retorna o caminho da requisição que causou o erro.
	 *
	 * @return Caminho da requisição
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Define o caminho da requisição que causou o erro.
	 *
	 * @param path Caminho da requisição
	 */
	public void setPath(String path) {
		this.path = path;
	}
}
