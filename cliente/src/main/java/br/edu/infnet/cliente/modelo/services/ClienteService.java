package br.edu.infnet.cliente.modelo.services;

import br.edu.infnet.cliente.modelo.entidades.Cliente;
import br.edu.infnet.cliente.modelo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente getByCodigo(Long codigo) {
        return clienteRepository.findById(codigo).get();
    }
}
