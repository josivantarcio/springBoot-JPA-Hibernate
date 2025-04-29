package com.educandoweb.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.services.UserService;

/**
 * Controlador REST responsável por expor endpoints relacionados a usuários.
 * Implementa operações CRUD através de requisições HTTP.
 */
@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired 
	private UserService service;
	
	/**
	 * Retorna todos os usuários cadastrados no sistema.
	 *
	 * @return ResponseEntity contendo a lista de usuários e status HTTP 200 (OK)
	 */
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	/**
	 * Busca um usuário pelo ID.
	 *
	 * @param id ID do usuário a ser encontrado
	 * @return ResponseEntity contendo o usuário encontrado e status HTTP 200 (OK)
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	/**
	 * Insere um novo usuário no sistema.
	 *
	 * @param obj Usuário a ser inserido
	 * @return ResponseEntity contendo o usuário inserido, URI do recurso criado e status HTTP 201 (Created)
	 */
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	/**
	 * Remove um usuário do sistema.
	 *
	 * @param id ID do usuário a ser removido
	 * @return ResponseEntity com status HTTP 204 (No Content)
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * Atualiza os dados de um usuário existente.
	 *
	 * @param id ID do usuário a ser atualizado
	 * @param obj Dados atualizados do usuário
	 * @return ResponseEntity contendo o usuário atualizado e status HTTP 200 (OK)
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj) {
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
