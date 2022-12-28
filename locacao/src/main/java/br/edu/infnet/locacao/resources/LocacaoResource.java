package br.edu.infnet.locacao.resources;

import br.edu.infnet.locacao.clients.EquipamentoClient;
import br.edu.infnet.locacao.entidades.Locacao;
import br.edu.infnet.locacao.resources.dto.ClienteDTO;
import br.edu.infnet.locacao.resources.dto.EquipamentoCatalogoDTO;
import br.edu.infnet.locacao.resources.dto.LocacaoDTO;
import br.edu.infnet.locacao.resources.dto.LocacaoResponseDTO;
import br.edu.infnet.locacao.services.LocacaoService;
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

import java.util.ArrayList;
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

    @Autowired
    private LocacaoService locacaoService;

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
        locacaoService.save(locacao);

        return new LocacaoResponseDTO(clienteDTO, equipamentos.getBody());

    }
}
