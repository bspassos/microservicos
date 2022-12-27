package br.edu.infnet.catalogo.modelo.repository;

import br.edu.infnet.catalogo.modelo.entidades.Equipamento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipamentoRepository extends CrudRepository<Equipamento, Integer> {

    @Query("from Equipamento e where e.id in :ids")
    List<Equipamento> findAllByIds(List<Integer> ids);
}
