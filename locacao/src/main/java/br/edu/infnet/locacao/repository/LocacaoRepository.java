package br.edu.infnet.locacao.repository;

import br.edu.infnet.locacao.entidades.Locacao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocacaoRepository  extends CrudRepository<Locacao, Integer> {

    @Query("from Locacao l where l.idCliente = :idCliente")
    List<Locacao> findAllByCliente(Integer idCliente);

}
