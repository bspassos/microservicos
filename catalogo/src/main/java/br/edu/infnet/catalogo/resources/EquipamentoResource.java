package br.edu.infnet.catalogo.resources;

import br.edu.infnet.catalogo.modelo.entidades.Equipamento;
import br.edu.infnet.catalogo.modelo.services.EquipamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/equipamentos")
public class EquipamentoResource {

    @Autowired
    private EquipamentoService equipamentoService;

    @GetMapping
    public List<Equipamento> getEquipamentos() {
        return equipamentoService.getAll();
    }

}
