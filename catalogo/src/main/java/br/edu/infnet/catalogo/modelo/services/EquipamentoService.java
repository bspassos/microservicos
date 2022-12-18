package br.edu.infnet.catalogo.modelo.services;

import br.edu.infnet.catalogo.modelo.entidades.Equipamento;
import br.edu.infnet.catalogo.modelo.repository.EquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipamentoService {

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    public List<Equipamento> getAll() {
        return (List<Equipamento>) equipamentoRepository.findAll();
    }
}
