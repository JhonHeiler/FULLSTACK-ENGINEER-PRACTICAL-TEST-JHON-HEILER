package com.example.alianzabackend.domain.usecase;


import com.example.alianzabackend.infrastruture.drivenapdaters.dto.ClientDTO;
import com.example.alianzabackend.infrastruture.drivenapdaters.input.ClientInput;
import com.example.alianzabackend.domain.model.Client;
import com.example.alianzabackend.domain.model.gateway.ClientRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private static final Logger logger = LogManager.getLogger(ClientService.class);

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
        logger.info("ClientService initialized");
    }

    public Page<ClientDTO> getClientsPage(int page, int size) {
        logger.info("Getting clients page {} with size {}", page, size);
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Client> clientsPage = clientRepository.findAll(pageRequest);
        Page<ClientDTO> clientDTOsPage = clientsPage.map(ClientDTO::fromDomain);
        logger.info("Retrieved {} clients", clientDTOsPage.getContent().size());
        return clientDTOsPage;
    }

    public ClientDTO searchClientBySharedKey(String sharedKey) {
        logger.info("Searching client by sharedKey: {}", sharedKey);
        Client client = clientRepository.findBySharedKey(sharedKey);
        logger.info("Found client: {}", client);
        return ClientDTO.fromDomain(client);
    }
    public List<ClientDTO> searchClients(String name, String email, String phone,
                                         LocalDate startDate, LocalDate endDate) {
        logger.info("Searching clients with parameters - Name: {}, Email: {}, Phone: {}, StartDate: {}, EndDate: {}", name, email, phone, startDate, endDate);
        List<Client> clients = clientRepository.searchClients(name, email, phone, startDate, endDate);
        List<ClientDTO> clientDTOs = clients.stream()
                .map(ClientDTO::fromDomain)
                .collect(Collectors.toList());
        logger.info("Found {} clients", clientDTOs.size());
        return clientDTOs;
    }

    public ClientDTO createClient(ClientInput clientInput) {
        logger.info("Creating new client: {}", clientInput);
        String uniqueSharedKey = generateSharedKey(clientInput.getName());
        Client client = new Client();
        client.setSharedKey(uniqueSharedKey);
        client.setName(clientInput.getName());
        client.setEmail(clientInput.getEmail());
        client.setPhone(clientInput.getPhone());
        client.setStartDate(clientInput.getStartDate());
        client.setEndDate(clientInput.getEndDate());

        Client clientSave = clientRepository.save(client);
        logger.info("Client created successfully: {}", clientSave);
        return ClientDTO.fromDomain(clientSave);
    }

    private String generateSharedKey(String name) {
        logger.info("Generating sharedKey for name: {}", name);
        if (name.contains(" ")) {
            String[] words = name.split(" ", 2);
            String sharedKey = words[0].substring(0, 1) + words[1];
            logger.info("Generated sharedKey: {}", sharedKey);
            return sharedKey;
        } else {
            logger.info("Name does not contain space, returning name as sharedKey");
            return name;
        }
    }

}
