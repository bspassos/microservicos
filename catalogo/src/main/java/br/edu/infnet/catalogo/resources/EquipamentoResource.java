package br.edu.infnet.catalogo.resources;

import br.edu.infnet.catalogo.modelo.entidades.Equipamento;
import br.edu.infnet.catalogo.modelo.services.EquipamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/equipamentos")
public class EquipamentoResource {

    @Autowired
    private EquipamentoService equipamentoService;


    @PostMapping
    public ResponseEntity<Object> insert(@RequestBody Equipamento equipamento) {
        try {

            return ResponseEntity.status(HttpStatus.CREATED).body(equipamentoService.save(equipamento));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao tentar inserir o equipamento!");
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAll() {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(equipamentoService.findAll());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao tentar listar os equipamentos!");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable(value = "id") Integer id) {
        try {

            Optional<Equipamento> equipamentoOptional = equipamentoService.findById(id);

            if(equipamentoOptional.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Equipamento não encontrado!");
            }

            return ResponseEntity.status(HttpStatus.OK).body(equipamentoOptional.get());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao tentar buscar o equipamento!");
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Integer id, @RequestBody Equipamento equipamento) {
        try {

            Optional<Equipamento> equipamentoOptional = equipamentoService.findById(id);

            if(equipamentoOptional.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Equipamento não encontrado!");
            }

            Equipamento currentEquipamento = equipamentoOptional.get();
            if (equipamento.getNome() != null) currentEquipamento.setNome(equipamento.getNome());
            if (equipamento.getMensalidade() != 0) currentEquipamento.setMensalidade(equipamento.getMensalidade());

            return ResponseEntity.status(HttpStatus.OK).body(equipamentoService.save(currentEquipamento));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao tentar atualizar o equipamento!");
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Integer id) {
        try {

            Optional<Equipamento> equipamentoOptional = equipamentoService.findById(id);

            if(equipamentoOptional.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Equipamento não encontrado!");
            }

            equipamentoService.delete(equipamentoOptional.get());

            return ResponseEntity.status(HttpStatus.OK).body("Equipamento deletado!");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao tentar deletar o equipamento!");
        }
    }

}
