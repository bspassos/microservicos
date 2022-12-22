package br.edu.infnet.cliente.modelo.resources;

import br.edu.infnet.cliente.modelo.entidades.Cliente;
import br.edu.infnet.cliente.modelo.services.ClienteService;
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

import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

    private static Logger log = LoggerFactory.getLogger(ClienteResource.class);

    @Autowired
    private ClienteService clienteService;


    @Operation(summary = "Cadastrar um cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente cadastrado",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))
                    }
            )
    })
    @PostMapping
    public ResponseEntity<Object> insert(@RequestBody Cliente cliente) {
        try {

            return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(cliente));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao tentar inserir o cliente!");
        }
    }

    @Operation(summary = "Listar clientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clientes",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Cliente.class)))
                    }
            )
    })
    @GetMapping
    public ResponseEntity<Object> getAll() {

        log.info("API de Cliente");

        try{
            return ResponseEntity.status(HttpStatus.OK).body(clienteService.findAll());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao tentar listar os clientes!");
        }
    }

    @Operation(summary = "Detalhar um cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado!",
                    content = @Content
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable(value = "id") Integer id) {

        log.info("Chamada a api de clientes com id: {}", id);

        try {

            Optional<Cliente> clienteOptional = clienteService.findById(id);

            if(clienteOptional.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
            }

            return ResponseEntity.status(HttpStatus.OK).body(clienteOptional.get());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao tentar buscar o cliente!");
        }
    }


    @Operation(summary = "Alterar um cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente alterado",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado!",
                    content = @Content
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Integer id, @RequestBody Cliente cliente) {
        try {

            Optional<Cliente> clienteOptional = clienteService.findById(id);

            if(clienteOptional.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
            }

            Cliente currentCliente = clienteOptional.get();
            if (cliente.getNome() != null) currentCliente.setNome(cliente.getNome());
            if (cliente.getEmail() != null) currentCliente.setEmail(cliente.getEmail());
            if (cliente.getEndereco() != null) currentCliente.setEndereco(cliente.getEndereco());

            return ResponseEntity.status(HttpStatus.OK).body(clienteService.save(currentCliente));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao tentar atualizar o cliente!");
        }
    }


    @Operation(summary = "Deletar um cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente deletado!",
                    content = @Content
            ),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado!",
                    content = @Content
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Integer id) {
        try {

            Optional<Cliente> clienteOptional = clienteService.findById(id);

            if(clienteOptional.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
            }

            clienteService.delete(clienteOptional.get());

            return ResponseEntity.status(HttpStatus.OK).body("Cliente deletado!");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao tentar deletar o cliente!");
        }
    }

}
