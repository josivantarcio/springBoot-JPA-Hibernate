package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

/**
 * Serviço responsável por gerenciar operações relacionadas a usuários.
 * Implementa operações CRUD (Create, Read, Update, Delete) e lógica de negócio específica.
 */
@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	/**
	 * Retorna todos os usuários cadastrados no sistema.
	 *
	 * @return Lista de usuários
	 */
	public List<User> findAll() {
		return repository.findAll();
	}

	/**
	 * Busca um usuário pelo ID.
	 *
	 * @param id ID do usuário a ser encontrado
	 * @return Usuário encontrado
	 * @throws ResourceNotFoundException se o usuário não for encontrado
	 */
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	/**
	 * Insere um novo usuário no sistema.
	 *
	 * @param obj Usuário a ser inserido
	 * @return Usuário inserido com ID gerado
	 */
	public User insert(User obj) {
		return repository.save(obj);
	}
	
	/**
	 * Remove um usuário do sistema.
	 *
	 * @param id ID do usuário a ser removido
	 * @throws ResourceNotFoundException se o usuário não for encontrado
	 * @throws DatabaseException se houver violação de integridade referencial
	 */
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	/**
	 * Atualiza os dados de um usuário existente.
	 *
	 * @param id ID do usuário a ser atualizado
	 * @param obj Dados atualizados do usuário
	 * @return Usuário atualizado
	 * @throws ResourceNotFoundException se o usuário não for encontrado
	 */
	public User update(Long id, User obj) {
		try {
			User entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}	
	}

	/**
	 * Atualiza os dados de um usuário existente com base nos dados fornecidos.
	 * Atualiza apenas os campos permitidos: nome, email e telefone.
	 *
	 * @param entity Usuário existente no banco de dados
	 * @param obj Usuário com os dados atualizados
	 */
	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
}
