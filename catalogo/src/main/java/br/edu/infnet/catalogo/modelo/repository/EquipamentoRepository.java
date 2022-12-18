package br.edu.infnet.catalogo.modelo.repository;

import br.edu.infnet.catalogo.modelo.entidades.Equipamento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipamentoRepository extends CrudRepository<Equipamento, Long> {


}
