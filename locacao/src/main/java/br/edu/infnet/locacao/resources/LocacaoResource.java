package br.edu.infnet.locacao.resources;

import br.edu.infnet.locacao.resources.dto.ClienteDTO;
import br.edu.infnet.locacao.resources.dto.LocacaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/locacoes")
public class LocacaoResource {


    @Autowired
    private RestTemplate restTemplate;

    @PostMapping
    public void efetuaLocacao(@RequestBody LocacaoDTO locacaoDTO) {

        ClienteDTO clienteDTO = restTemplate.getForObject("http://localhost:8282/clientes/"+locacaoDTO.getClienteId(), ClienteDTO.class);

        System.out.println(clienteDTO);
        System.out.println(locacaoDTO);
    }
}
