package com.gabrielvicente.dsclients.service;

import com.gabrielvicente.dsclients.dto.ClientDTO;
import com.gabrielvicente.dsclients.entity.Client;
import com.gabrielvicente.dsclients.repository.ClientRepository;
import com.gabrielvicente.dsclients.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public List<ClientDTO> findAll() {
        List<Client> clients = repository.findAll();
        return clients.stream().map(ClientDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Optional<Client> optionalClient = repository.findById(id);
        Client client = optionalClient.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO save(ClientDTO dto) {
        Client client = new Client();
        convertToEntity(dto, client);
        return new ClientDTO(repository.save(client));
    }

    @Transactional
    public ClientDTO update(ClientDTO dto, Long id) {
        try {
            Client client = repository.getOne(id);
            convertToEntity(dto, client);
            return new ClientDTO(client);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Id " + id + " not found");
        }
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private void convertToEntity(ClientDTO dto, Client client) {
        client.setName(dto.getName());
        client.setCpf(dto.getCpf());
        client.setIncome(dto.getIncome());
        client.setBirthDate(dto.getBirthDate());
        client.setChildren(dto.getChildren());
    }
}
