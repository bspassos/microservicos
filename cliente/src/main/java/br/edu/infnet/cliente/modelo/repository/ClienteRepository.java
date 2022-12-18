package br.edu.infnet.cliente.modelo.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import br.edu.infnet.cliente.modelo.entidades.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {

}
