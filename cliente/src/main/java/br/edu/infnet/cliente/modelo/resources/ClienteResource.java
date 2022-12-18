package br.edu.infnet.cliente.modelo.resources;

import br.edu.infnet.cliente.modelo.entidades.Cliente;
import br.edu.infnet.cliente.modelo.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{codigo}")
    public Cliente getCliente(@PathVariable Long codigo) {
        return clienteService.getByCodigo(codigo);
    }

}
