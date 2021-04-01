package com.gabrielvicente.dsclients.service;

import com.gabrielvicente.dsclients.entity.Client;
import com.gabrielvicente.dsclients.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public List<Client> findAll() {
        return repository.findAll();
    }
}
