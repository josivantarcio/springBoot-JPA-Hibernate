package com.educandoweb.course.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.entities.enums.OrderStatus;
import com.educandoweb.course.repositories.OrderRepository;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository repository;

    @InjectMocks
    private OrderService service;

    private Order order;
    private Long existingId;
    private Long nonExistingId;
    private User user;

    @BeforeEach
    void setUp() {
        existingId = 1L;
        nonExistingId = 2L;
        user = new User(existingId, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        order = new Order(existingId, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, user);
    }

    @Test
    void findByIdShouldReturnOrderWhenIdExists() {
        when(repository.findById(existingId)).thenReturn(Optional.of(order));

        Order result = service.findById(existingId);

        assertNotNull(result);
        assertEquals(existingId, result.getId());
        assertEquals(OrderStatus.PAID, result.getOrderStatus());
        assertEquals(user, result.getClient());
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
    void findAllShouldReturnListOfOrders() {
        List<Order> orders = List.of(order);
        when(repository.findAll()).thenReturn(orders);

        List<Order> result = service.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(order, result.get(0));
        verify(repository, times(1)).findAll();
    }
} 