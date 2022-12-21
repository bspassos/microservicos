package br.edu.infnet.catalogo.modelo.services;

import br.edu.infnet.catalogo.modelo.entidades.Equipamento;
import br.edu.infnet.catalogo.modelo.repository.EquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class EquipamentoService {

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    @Transactional
    public Equipamento save(Equipamento equipamento) {
        return equipamentoRepository.save(equipamento);
    }

    public Collection<Equipamento> findAll() {
        return (Collection<Equipamento>) equipamentoRepository.findAll();
    }

    public Optional<Equipamento> findById(Integer id) {
        return equipamentoRepository.findById(id);
    }

    public void delete(Equipamento equipamento) {
        equipamentoRepository.delete(equipamento);
    }
}
