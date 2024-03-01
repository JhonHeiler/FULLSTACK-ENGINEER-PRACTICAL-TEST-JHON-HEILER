package com.example.alianzabackend.domain.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
    Client client = new Client();

    @Test
    void getId() {
        client.setId(1L);
        assertEquals(1L, client.getId());
    }

    @Test
    void getSharedKey() {
        client.setSharedKey("key");
        assertEquals("key", client.getSharedKey());
    }

    @Test
    void getName() {
        client.setName("Test");
        assertEquals("Test", client.getName());
    }

    @Test
    void getEmail() {
        client.setEmail("test@example.com");
        assertEquals("test@example.com", client.getEmail());
    }

    @Test
    void getPhone() {
        client.setPhone("1234567890");
        assertEquals("1234567890", client.getPhone());
    }

    @Test
    void getStartDate() {
        LocalDate startDate = LocalDate.now();
        client.setStartDate(startDate);
        assertEquals(startDate, client.getStartDate());
    }

    @Test
    void getEndDate() {
        LocalDate endDate = LocalDate.now();
        client.setEndDate(endDate);
        assertEquals(endDate, client.getEndDate());
    }

    @Test
    void setId() {
        client.setId(2L);
        assertEquals(2L, client.getId());
    }

    @Test
    void setSharedKey() {
        client.setSharedKey("newKey");
        assertEquals("newKey", client.getSharedKey());
    }

    @Test
    void setName() {
        client.setName("New Test");
        assertEquals("New Test", client.getName());
    }

    @Test
    void setEmail() {
        client.setEmail("newtest@example.com");
        assertEquals("newtest@example.com", client.getEmail());
    }

    @Test
    void setPhone() {
        client.setPhone("0987654321");
        assertEquals("0987654321", client.getPhone());
    }

    @Test
    void setStartDate() {
        LocalDate newStartDate = LocalDate.now();
        client.setStartDate(newStartDate);
        assertEquals(newStartDate, client.getStartDate());
    }

    @Test
    void setEndDate() {
        LocalDate newEndDate = LocalDate.now();
        client.setEndDate(newEndDate);
        assertEquals(newEndDate, client.getEndDate());
    }
}
