package br.edu.infnet.catalogo.resources;

import br.edu.infnet.catalogo.modelo.entidades.Equipamento;
import br.edu.infnet.catalogo.modelo.services.EquipamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/equipamentos")
public class EquipamentoResource {

    private static Logger log = LoggerFactory.getLogger(EquipamentoResource.class);

    @Autowired
    private EquipamentoService equipamentoService;


    @Operation(summary = "Cadastrar um equipamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Equipamento cadastrado",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Equipamento.class))
                    }
            )
    })
    @PostMapping
    public ResponseEntity<Object> insert(@RequestBody Equipamento equipamento) {

        log.info("Chamada para api de catalogo para inserir equipamento com dados {}", equipamento);

        try {

            return ResponseEntity.status(HttpStatus.CREATED).body(equipamentoService.save(equipamento));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao tentar inserir o equipamento!");
        }
    }

    @Operation(summary = "Listar equipamentos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipamentos",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Equipamento.class)))
                    }
            )
    })
    @GetMapping
    public ResponseEntity<Object> getAll() {

        log.info("Chamada a api de catalogo para lista equipamentos");

        try{
            return ResponseEntity.status(HttpStatus.OK).body(equipamentoService.findAll());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao tentar listar os equipamentos!");
        }
    }

    @Operation(summary = "Listar equipamentos por lista ids")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipamentos por lista de ids",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Equipamento.class)))
                    }
            )
    })
    @PostMapping("/search")
    public ResponseEntity<Object> getAllByIds(@RequestBody List<Equipamento> equipamentos) {

        log.info("Chamada a api de catalogo para lista equipamentos por id");

        try{
            return ResponseEntity.status(HttpStatus.OK).body(equipamentoService.findAllByIds(equipamentos));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
                    //.body("Ocorreu um erro ao tentar listar os equipamentos por id!");
        }
    }

    @Operation(summary = "Detalhar um equipamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipamento",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Equipamento.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Equipamento não encontrado!",
                    content = @Content
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable(value = "id") Integer id) {

        log.info("Chamada a api de catalogo para pegar equipamento com id: {}", id);

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


    @Operation(summary = "Alterar um equipamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipamento alterado",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Equipamento.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Equipamento não encontrado!",
                    content = @Content
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Integer id, @RequestBody Equipamento equipamento) {

        log.info("Chamada a api de catalogo para alterar equipamento com id: {} e com os dados {}", id, equipamento);

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


    @Operation(summary = "Deletar um equipamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipamento deletado!",
                    content = @Content
            ),
            @ApiResponse(responseCode = "404", description = "Equipamento não encontrado!",
                    content = @Content
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Integer id) {

        log.info("Chamada a api de catalogo para deletar equipamento com id: {}", id);

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
