package br.edu.infnet.locacao.services;

import br.edu.infnet.locacao.entidades.Locacao;
import br.edu.infnet.locacao.repository.LocacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocacaoService {

    @Autowired
    private LocacaoRepository locacaoRepository;

    public Locacao save(Locacao locacao) {
        return locacaoRepository.save(locacao);
    }
}
