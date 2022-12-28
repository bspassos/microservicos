package br.edu.infnet.locacao.repository;

import br.edu.infnet.locacao.entidades.Locacao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocacaoRepository  extends CrudRepository<Locacao, Integer> {
}
