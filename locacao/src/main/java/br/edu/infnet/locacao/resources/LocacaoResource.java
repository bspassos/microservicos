package br.edu.infnet.locacao.resources;

import br.edu.infnet.locacao.clients.EquipamentoClient;
import br.edu.infnet.locacao.resources.dto.ClienteDTO;
import br.edu.infnet.locacao.resources.dto.EquipamentoCatalogoDTO;
import br.edu.infnet.locacao.resources.dto.LocacaoDTO;
import br.edu.infnet.locacao.resources.dto.LocacaoResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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

    @PostMapping
    public LocacaoResponseDTO efetuaLocacao(@RequestBody LocacaoDTO locacaoDTO) {

        log.info("solicitacao para locacao com a informacao: {}", locacaoDTO);

        if(log.isDebugEnabled()){
            log.debug("Debug ligado");
        }
        ClienteDTO clienteDTO = restTemplate.getForObject(clienteApiUrl+locacaoDTO.getClienteId(), ClienteDTO.class);

        System.out.println(clienteDTO);
        System.out.println(locacaoDTO);
        log.info("Chamada a api de clientes realizada: {}", clienteDTO);

        ResponseEntity<List<EquipamentoCatalogoDTO>> equipamentos = equipamentoClient.getEquipamentos();
        System.out.println(equipamentos.getBody());

        return new LocacaoResponseDTO(clienteDTO, equipamentos.getBody());

    }
}
