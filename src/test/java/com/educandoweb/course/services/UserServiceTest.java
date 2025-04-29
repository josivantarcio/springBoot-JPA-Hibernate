package com.educandoweb.course.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService service;

    private User user;
    private Long existingId;
    private Long nonExistingId;
    private Long dependentId;

    @BeforeEach
    void setUp() {
        existingId = 1L;
        nonExistingId = 2L;
        dependentId = 3L;
        user = new User(existingId, "Maria Brown", "maria@gmail.com", "988888888", "123456");
    }

    @Test
    void findByIdShouldReturnUserWhenIdExists() {
        when(repository.findById(existingId)).thenReturn(Optional.of(user));

        User result = service.findById(existingId);

        assertNotNull(result);
        assertEquals(existingId, result.getId());
        assertEquals("Maria Brown", result.getName());
        assertEquals("maria@gmail.com", result.getEmail());
        verify(repository, times(1)).findById(existingId);
    }

    @Test
    void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
        when(repository.findById(nonExistingId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            service.findById(nonExistingId);
        });
        verify(repository, times(1)).findById(nonExistingId);
    }

    @Test
    void findAllShouldReturnListOfUsers() {
        List<User> users = List.of(user);
        when(repository.findAll()).thenReturn(users);

        List<User> result = service.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(user, result.get(0));
        verify(repository, times(1)).findAll();
    }

    @Test
    void insertShouldReturnUser() {
        when(repository.save(user)).thenReturn(user);

        User result = service.insert(user);

        assertNotNull(result);
        assertEquals(user, result);
        verify(repository, times(1)).save(user);
    }

    @Test
    void deleteShouldDoNothingWhenIdExists() {
        doNothing().when(repository).deleteById(existingId);

        assertDoesNotThrow(() -> {
            service.delete(existingId);
        });
        verify(repository, times(1)).deleteById(existingId);
    }

    @Test
    void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
        doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(nonExistingId);

        assertThrows(ResourceNotFoundException.class, () -> {
            service.delete(nonExistingId);
        });
        verify(repository, times(1)).deleteById(nonExistingId);
    }

    @Test
    void deleteShouldThrowDatabaseExceptionWhenDependentId() {
        doThrow(DataIntegrityViolationException.class).when(repository).deleteById(dependentId);

        assertThrows(DatabaseException.class, () -> {
            service.delete(dependentId);
        });
        verify(repository, times(1)).deleteById(dependentId);
    }

    @Test
    void updateShouldReturnUserWhenIdExists() {
        User updatedUser = new User(existingId, "Maria Silva", "maria.silva@gmail.com", "999999999", "123456");
        when(repository.getReferenceById(existingId)).thenReturn(user);
        when(repository.save(user)).thenReturn(user);

        User result = service.update(existingId, updatedUser);

        assertNotNull(result);
        assertEquals(existingId, result.getId());
        assertEquals("Maria Silva", result.getName());
        assertEquals("maria.silva@gmail.com", result.getEmail());
        verify(repository, times(1)).getReferenceById(existingId);
        verify(repository, times(1)).save(user);
    }

    @Test
    void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
        User updatedUser = new User(nonExistingId, "Maria Silva", "maria.silva@gmail.com", "999999999", "123456");
        when(repository.getReferenceById(nonExistingId)).thenThrow(EntityNotFoundException.class);

        assertThrows(ResourceNotFoundException.class, () -> {
            service.update(nonExistingId, updatedUser);
        });
        verify(repository, times(1)).getReferenceById(nonExistingId);
    }
} 