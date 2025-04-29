package com.educandoweb.course.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Classe responsável por tratar exceções lançadas pelos controladores REST.
 * Implementa um tratamento centralizado de exceções, convertendo-as em respostas HTTP apropriadas.
 */
@ControllerAdvice
public class ResourceExceptionHandler {

	/**
	 * Trata exceções do tipo ResourceNotFoundException.
	 * Retorna uma resposta HTTP 404 (Not Found) com detalhes do erro.
	 *
	 * @param e Exceção lançada
	 * @param request Requisição HTTP que causou a exceção
	 * @return ResponseEntity contendo os detalhes do erro
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	/**
	 * Trata exceções do tipo DatabaseException.
	 * Retorna uma resposta HTTP 400 (Bad Request) com detalhes do erro.
	 *
	 * @param e Exceção lançada
	 * @param request Requisição HTTP que causou a exceção
	 * @return ResponseEntity contendo os detalhes do erro
	 */
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
		String error = "Database error";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
