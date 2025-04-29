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

import com.educandoweb.course.entities.Product;
import com.educandoweb.course.repositories.ProductRepository;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService service;

    private Product product;
    private Long existingId;
    private Long nonExistingId;

    @BeforeEach
    void setUp() {
        existingId = 1L;
        nonExistingId = 2L;
        product = new Product(existingId, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
    }

    @Test
    void findByIdShouldReturnProductWhenIdExists() {
        when(repository.findById(existingId)).thenReturn(Optional.of(product));

        Product result = service.findById(existingId);

        assertNotNull(result);
        assertEquals(existingId, result.getId());
        assertEquals("The Lord of the Rings", result.getName());
        assertEquals(90.5, result.getPrice());
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
    void findAllShouldReturnListOfProducts() {
        List<Product> products = List.of(product);
        when(repository.findAll()).thenReturn(products);

        List<Product> result = service.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(product, result.get(0));
        verify(repository, times(1)).findAll();
    }
} 