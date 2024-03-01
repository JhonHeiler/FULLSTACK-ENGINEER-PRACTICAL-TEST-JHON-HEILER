package com.example.alianzabackend.infrastruture.entrypoints;

import com.example.alianzabackend.domain.usecase.ClientService;
import com.example.alianzabackend.infrastruture.drivenapdaters.dto.ClientDTO;
import com.example.alianzabackend.infrastruture.drivenapdaters.input.ClientInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


class ClientControllerTest {

    @Mock
    ClientService clientService;
    @InjectMocks
    ClientController clientController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getClientsPage_success() throws Exception {
        int page = 0;
        int size = 10;
        List<ClientDTO> mockClients = Arrays.asList(
                new ClientDTO("jmosque", "Nombre1", "correo1@example.com", "1234567890", "2024-02-05", "2024-02-06"),
                new ClientDTO("hTest", "Nombre2", "correo2@example.com", "0987654321", "2024-02-08", "2024-02-09")
        );
        Page<ClientDTO> mockPage = new PageImpl<>(mockClients);
        when(clientService.getClientsPage(page, size)).thenReturn(mockPage);
        ResponseEntity<List<ClientDTO>> response = clientController.getclients(page, size);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getHeaders().containsKey("X-Total-Count"));
        assertEquals(String.valueOf(mockClients.size()), response.getHeaders().getFirst("X-Total-Count"));
        assertEquals(mockClients, response.getBody());
    }
    @Test
    void searchClientBySharedKey_success() throws Exception {
        String sharedKey = "jmosque";
        ClientDTO mockClient = new ClientDTO("jmosque", "Nombre1", "correo1@example.com", "1234567890", "2024-02-05" , "2024-02-06");

        when(clientService.searchClientBySharedKey(sharedKey)).thenReturn(mockClient);

        ResponseEntity<List<ClientDTO>> response = clientController.searchClientBySharedKey(sharedKey);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(mockClient, response.getBody().get(0));
    }

    @Test
    void searchClientBySharedKey_notFound() throws Exception {
        String sharedKey = "unknown";

        when(clientService.searchClientBySharedKey(sharedKey)).thenReturn(null);

        ResponseEntity<List<ClientDTO>> response = clientController.searchClientBySharedKey(sharedKey);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void searchClients_success() throws Exception {
        String name = "Nombre1";
        String email = "correo1@example.com";
        String phone = "1234567890";
        LocalDate startDate = LocalDate.parse("2024-02-05");
        LocalDate endDate = LocalDate.parse("2024-02-06");

        List<ClientDTO> mockClients = Arrays.asList(
                new ClientDTO("jmosque", "Nombre1", "correo1@example.com", "1234567890", "2024-02-05", "2024-02-06")
        );

        when(clientService.searchClients(name, email, phone, startDate, endDate)).thenReturn(mockClients);

        ResponseEntity<List<ClientDTO>> response = clientController.searchClients(name, email, phone, startDate, endDate);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockClients, response.getBody());
    }

    @Test
    void createClient_success() throws Exception {
        String name = "Nombre1";
        String email = "correo1@example.com";
        String phone = "1234567890";
        LocalDate startDate = LocalDate.parse("2024-02-05");
        LocalDate endDate = LocalDate.parse("2024-02-06");

        ClientInput clientInput = new ClientInput(name, email, phone, startDate, endDate);
        when(clientService.createClient(clientInput)).thenReturn(any());

        ResponseEntity<Object> response = clientController.createClient(clientInput);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clientInput, response.getBody());
    }
}