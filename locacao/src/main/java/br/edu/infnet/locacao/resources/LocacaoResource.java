package br.edu.infnet.locacao.resources;

import br.edu.infnet.locacao.clients.EquipamentoClient;
import br.edu.infnet.locacao.entidades.Locacao;
import br.edu.infnet.locacao.resources.dto.ClienteDTO;
import br.edu.infnet.locacao.resources.dto.EquipamentoCatalogoDTO;
import br.edu.infnet.locacao.resources.dto.LocacaoDTO;
import br.edu.infnet.locacao.resources.dto.LocacaoResponseDTO;
import br.edu.infnet.locacao.services.LocacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/locacoes")
public class LocacaoResource {

    private static Logger log = LoggerFactory.getLogger(LocacaoResource.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${cliente.endpoint.url}")
    private String clienteApiUrl;

    @Autowired
    private EquipamentoClient equipamentoClient;

    @Autowired
    private LocacaoService locacaoService;

    @Operation(summary = "Efetuar uma locação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tarefa cadastrada",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = LocacaoResponseDTO.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado!",
                    content = @Content
            )
    })
    @PostMapping
    public LocacaoResponseDTO efetuaLocacao(@RequestBody LocacaoDTO locacaoDTO) {

        log.info("Chamada para api de locacao para solicitar locacao com dados {}", locacaoDTO);

        if(log.isDebugEnabled()){
            log.debug("Debug ligado");
        }

        ClienteDTO clienteDTO = restTemplate.getForObject(clienteApiUrl+locacaoDTO.getClienteId(), ClienteDTO.class);

        log.info("Chamada a api de clientes através da api de locação com os dados {}", clienteDTO);

        ResponseEntity<List<EquipamentoCatalogoDTO>> equipamentos = equipamentoClient.getEquipamentos(locacaoDTO.getEquipamentos());

        log.info("Chamada a api de equipamentos através da api de locação com os dados {}", equipamentos.getBody());

        List<Integer> ids = new ArrayList<>();
        for (EquipamentoCatalogoDTO equipamentoCatalogoDTO : locacaoDTO.getEquipamentos()) {
            ids.add(equipamentoCatalogoDTO.getId());
        }

        Locacao locacao = new Locacao(locacaoDTO.getMeses(), locacaoDTO.getClienteId(), ids);
        Locacao locacaoSalva = locacaoService.save(locacao);

        LocacaoResponseDTO locacaoResponseDTO = new LocacaoResponseDTO(clienteDTO, equipamentos.getBody());
        locacaoResponseDTO.setId(locacaoSalva.getId());
        locacaoResponseDTO.setData(locacaoSalva.getData());
        locacaoResponseDTO.setMeses(locacaoSalva.getMeses());

        return locacaoResponseDTO;

    }

    @Operation(summary = "Listar todas as locações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Locações",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = LocacaoResponseDTO.class)))
                    }
            )
    })
    @GetMapping
    public ResponseEntity<Object> getAll(){

        log.info("Chamada para api de locação para listar locações");

        try{
            List<Locacao> locacoes = (List<Locacao>) locacaoService.findAll();

            List<LocacaoResponseDTO> locacaoResponseDTOS = new ArrayList<>();

            for (Locacao locacao: locacoes) {

                log.info("Chamada para api de cliente para pegar o cliente da locacao {}", locacao.getId());

                ClienteDTO clienteDTO = restTemplate.getForObject(clienteApiUrl+locacao.getIdCliente(), ClienteDTO.class);

                List<EquipamentoCatalogoDTO> equipamentosCatalogo = new ArrayList<>();
                for (Integer id : locacao.getIdEquipamento()) {
                    EquipamentoCatalogoDTO equipamentoCatalogoDTO = new EquipamentoCatalogoDTO();
                    equipamentoCatalogoDTO.setId(id);
                    equipamentosCatalogo.add(equipamentoCatalogoDTO);
                }

                log.info("Chamada para api de catálogo para pegar os equipamentos da locacao {}", locacao.getId());

                ResponseEntity<List<EquipamentoCatalogoDTO>> equipamentos = equipamentoClient.getEquipamentos(equipamentosCatalogo);

                LocacaoResponseDTO locacaoResponseDTO = new LocacaoResponseDTO(clienteDTO, equipamentos.getBody());
                locacaoResponseDTO.setId(locacao.getId());
                locacaoResponseDTO.setData(locacao.getData());
                locacaoResponseDTO.setMeses(locacao.getMeses());

                locacaoResponseDTOS.add(locacaoResponseDTO);
            }

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(locacaoResponseDTOS);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao tentar listar as locacoes!");
        }

    }

    @Operation(summary = "Listar as locações de um cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Locacações",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = LocacaoResponseDTO.class)))
                    }
            )
    })
    @GetMapping("cliente/{idCliente}")
    public ResponseEntity<Object> getAllByCliente(@PathVariable(value = "idCliente") Integer idCliente){

        log.info("Chamada para api de locação para listar locações do cliente {}", idCliente);

        try{
            List<Locacao> locacoes = (List<Locacao>) locacaoService.findAllByCliente(idCliente);

            List<LocacaoResponseDTO> locacaoResponseDTOS = new ArrayList<>();

            for (Locacao locacao: locacoes) {

                log.info("Chamada para api de cliente para pegar o cliente da locacao {}", locacao.getId());

                ClienteDTO clienteDTO = restTemplate.getForObject(clienteApiUrl+locacao.getIdCliente(), ClienteDTO.class);

                List<EquipamentoCatalogoDTO> equipamentosCatalogo = new ArrayList<>();
                for (Integer id : locacao.getIdEquipamento()) {
                    EquipamentoCatalogoDTO equipamentoCatalogoDTO = new EquipamentoCatalogoDTO();
                    equipamentoCatalogoDTO.setId(id);
                    equipamentosCatalogo.add(equipamentoCatalogoDTO);
                }

                log.info("Chamada para api de catálogo para pegar os equipamentos da locacao {}", locacao.getId());

                ResponseEntity<List<EquipamentoCatalogoDTO>> equipamentos = equipamentoClient.getEquipamentos(equipamentosCatalogo);

                LocacaoResponseDTO locacaoResponseDTO = new LocacaoResponseDTO(clienteDTO, equipamentos.getBody());
                locacaoResponseDTO.setId(locacao.getId());
                locacaoResponseDTO.setData(locacao.getData());
                locacaoResponseDTO.setMeses(locacao.getMeses());

                locacaoResponseDTOS.add(locacaoResponseDTO);
            }

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(locacaoResponseDTOS);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao tentar listar as locacoes!");
        }

    }

    @Operation(summary = "Detalhar uma locação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Locação",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = LocacaoResponseDTO.class))
                    }
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable(value = "id") Integer id){

        log.info("Chamada para api de locação para detalha locação com id {}", id);

        try{
            Optional<Locacao> locacaoOptional = locacaoService.findById(id);

            if(locacaoOptional.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
            }

            log.info("Chamada para api de cliente para pegar o cliente da locacao {}", locacaoOptional.get().getId());

            ClienteDTO clienteDTO = restTemplate.getForObject(clienteApiUrl+locacaoOptional.get().getIdCliente(), ClienteDTO.class);

            List<EquipamentoCatalogoDTO> equipamentosCatalogo = new ArrayList<>();
            for (Integer idEquipamento : locacaoOptional.get().getIdEquipamento()) {
                EquipamentoCatalogoDTO equipamentoCatalogoDTO = new EquipamentoCatalogoDTO();
                equipamentoCatalogoDTO.setId(idEquipamento);
                equipamentosCatalogo.add(equipamentoCatalogoDTO);
            }

            log.info("Chamada para api de catálogo para pegar os equipamentos da locacao {}", locacaoOptional.get().getId());

            ResponseEntity<List<EquipamentoCatalogoDTO>> equipamentos = equipamentoClient.getEquipamentos(equipamentosCatalogo);

            LocacaoResponseDTO locacaoResponseDTO = new LocacaoResponseDTO(clienteDTO, equipamentos.getBody());
            locacaoResponseDTO.setId(locacaoOptional.get().getId());
            locacaoResponseDTO.setData(locacaoOptional.get().getData());
            locacaoResponseDTO.setMeses(locacaoOptional.get().getMeses());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(locacaoResponseDTO);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao tentar listar as locacoes!");
        }

    }
}
