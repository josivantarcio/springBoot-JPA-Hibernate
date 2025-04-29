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

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.repositories.CategoryRepository;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository repository;

    @InjectMocks
    private CategoryService service;

    private Category category;
    private Long existingId;
    private Long nonExistingId;

    @BeforeEach
    void setUp() {
        existingId = 1L;
        nonExistingId = 2L;
        category = new Category(existingId, "Electronics");
    }

    @Test
    void findByIdShouldReturnCategoryWhenIdExists() {
        when(repository.findById(existingId)).thenReturn(Optional.of(category));

        Category result = service.findById(existingId);

        assertNotNull(result);
        assertEquals(existingId, result.getId());
        assertEquals("Electronics", result.getName());
        verify(repository, times(1)).findById(existingId);
    }

    @Test
    void findByIdShouldThrowExceptionWhenIdDoesNotExist() {
        when(repository.findById(nonExistingId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            service.findById(nonExistingId);
        });
        verify(repository, times(1)).findById(nonExistingId);
    }

    @Test
    void findAllShouldReturnListOfCategories() {
        List<Category> categories = List.of(category);
        when(repository.findAll()).thenReturn(categories);

        List<Category> result = service.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(category, result.get(0));
        verify(repository, times(1)).findAll();
    }
} 