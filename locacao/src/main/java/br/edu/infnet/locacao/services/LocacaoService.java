package br.edu.infnet.locacao.services;

import br.edu.infnet.locacao.entidades.Locacao;
import br.edu.infnet.locacao.repository.LocacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class LocacaoService {

    @Autowired
    private LocacaoRepository locacaoRepository;

    public Locacao save(Locacao locacao) {
        return locacaoRepository.save(locacao);
    }

    public Collection<Locacao> findAll() {
        return (Collection<Locacao>) locacaoRepository.findAll();
    }

    public Collection<Locacao> findAllByCliente(Integer idCliente) {
        return (Collection<Locacao>) locacaoRepository.findAllByCliente(idCliente);
    }

    public Optional<Locacao> findById(Integer id) {
        return locacaoRepository.findById(id);
    }
}
