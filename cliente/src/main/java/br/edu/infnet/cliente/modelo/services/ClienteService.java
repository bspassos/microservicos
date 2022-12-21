package br.edu.infnet.cliente.modelo.services;

import br.edu.infnet.cliente.modelo.entidades.Cliente;
import br.edu.infnet.cliente.modelo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    
    @Transactional
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Collection<Cliente> findAll() {
        return (Collection<Cliente>) clienteRepository.findAll();
    }

    public Optional<Cliente> findById(Integer id) {
        return clienteRepository.findById(id);
    }

    public void delete(Cliente cliente) {
        clienteRepository.delete(cliente);
    }
}
