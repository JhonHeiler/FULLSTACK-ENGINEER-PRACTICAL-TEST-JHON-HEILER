package com.example.alianzabackend.infrastruture.entrypoints;

import com.example.alianzabackend.domain.usecase.ClientService;
import com.example.alianzabackend.infrastruture.drivenapdaters.dto.ClientDTO;
import com.example.alianzabackend.infrastruture.drivenapdaters.input.ClientInput;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/client")
@CrossOrigin(origins = "http://localhost:4200")
public class ClientController {
    private static final Logger logger = LogManager.getLogger(ClientController.class);
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
        logger.info("ClientController initialized");
    }

    @GetMapping("/page")
    public ResponseEntity<List<ClientDTO>> getclients(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size) {
        logger.info("Getting clients page {} with size {}", page, size);
        Page<ClientDTO> clientsPage = clientService.getClientsPage(page, size);
        List<ClientDTO> clients = clientsPage.getContent();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(clientsPage.getTotalElements()));
        logger.info("Returning {} clients", clients.size());
        return ResponseEntity.ok().headers(headers).body(clients);
    }

    @GetMapping("/{sharedKey}")
    public ResponseEntity<List<ClientDTO>> searchClientBySharedKey(@PathVariable String sharedKey) {
        logger.info("Searching client by sharedKey: {}", sharedKey);
        ClientDTO client = clientService.searchClientBySharedKey(sharedKey);

        if (client == null) {
            logger.info("Client with sharedKey: {} not found", sharedKey);
            return ResponseEntity.notFound().build();
        } else {
            List<ClientDTO> listClient = Collections.singletonList(client);
            return ResponseEntity.ok(listClient);
        }
    }


    @GetMapping("/search")
    public ResponseEntity<List<ClientDTO>> searchClients(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        logger.info("Searching clients with parameters - name: {}, email: {}, phone: {}, startDate: {}, endDate: {}", name, email, phone, startDate, endDate);
        List<ClientDTO> clients = clientService.searchClients(name, email, phone, startDate, endDate);
        logger.info("Found {} clients", clients.size());
        return ResponseEntity.ok(clients);
    }

    @PostMapping
    public ResponseEntity<Object> createClient(@RequestBody @Valid ClientInput clientInput) {
        logger.info("Creating new client: {}", clientInput);
        clientService.createClient(clientInput);
        logger.info("Client created successfully: {}",clientInput);
        return ResponseEntity.ok(clientInput);
    }

}
