package com.example.alianzabackend.domain.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import com.example.alianzabackend.domain.model.Client;
import com.example.alianzabackend.domain.model.gateway.ClientRepository;
import com.example.alianzabackend.infrastruture.drivenapdaters.dto.ClientDTO;
import com.example.alianzabackend.infrastruture.drivenapdaters.input.ClientInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;


class ClientServiceTest {

    private  ClientRepository clientRepository;
    private ClientService clientService;
    private Client mockClient;
    @BeforeEach
    void setUp() {
        clientRepository = mock(ClientRepository.class);
        clientService = new ClientService(clientRepository);
        mockClient = new Client();
        mockClient.setStartDate(LocalDate.of(2024, 2, 5));
        mockClient.setEndDate(LocalDate.of(2024, 2, 5));
        mockClient.setSharedKey("N1");
    }
    @Test
    void testGetClientsPage() {
        int page = 0;
        int size = 5;
        PageRequest pageRequest = PageRequest.of(page, size);
        List<Client> mockClients = Arrays.asList(mockClient,mockClient);
        Page<Client> mockPage = new PageImpl<>(mockClients);
        when(clientRepository.findAll(pageRequest)).thenReturn(mockPage);
        Page<ClientDTO> resultPage = clientService.getClientsPage(page, size);
        assertEquals(2, resultPage.getContent().size());
        verify(clientRepository, times(1)).findAll(pageRequest);
    }

    @Test
    void testSearchClientBySharedKey() {
        String sharedKey = "N1";
        when(clientRepository.findBySharedKey(sharedKey)).thenReturn(mockClient);
        ClientDTO result = clientService.searchClientBySharedKey(sharedKey);
        assertEquals(ClientDTO.fromDomain(mockClient), result);
    }

    @Test
    void testSearchClients() {
        List<Client> mockClients = Arrays.asList(mockClient,mockClient);
        when(clientRepository.searchClients(any(), any(), any(), any(), any())).thenReturn(mockClients);
        List<ClientDTO> result = clientService.searchClients(any(), any(), any(), any(), any());
        assertEquals(mockClients.size(), result.size());
    }

    @Test
    void testCreateClient() {
        String name = "Nombre1";
        String email = "correo1@example.com";
        String phone = "1234567890";
        LocalDate startDate = LocalDate.parse("2024-02-05");
        LocalDate endDate = LocalDate.parse("2024-02-06");

        ClientInput clientInput = new ClientInput(name, email, phone, startDate, endDate);
        String generatedKey = "N1";

        when(clientRepository.save(any())).thenReturn(mockClient);
        ClientDTO result = clientService.createClient(clientInput);
        assertEquals(generatedKey, result.getSharedKey());
        verify(clientRepository, times(1)).save(any());
    }
}
