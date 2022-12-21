package br.edu.infnet.cliente.modelo.resources;

import br.edu.infnet.cliente.modelo.entidades.Cliente;
import br.edu.infnet.cliente.modelo.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;


    @PostMapping
    public ResponseEntity<Object> insert(@RequestBody Cliente cliente) {
        try {

            return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(cliente));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao tentar inserir o cliente!");
        }
    }
  
    @GetMapping
    public ResponseEntity<Object> getAll() {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(clienteService.findAll());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao tentar listar os clientes!");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable(value = "id") Integer id) {
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
