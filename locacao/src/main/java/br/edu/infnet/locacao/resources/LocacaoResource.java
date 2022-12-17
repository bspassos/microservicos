package br.edu.infnet.locacao.resources;

import br.edu.infnet.locacao.resources.dto.LocacaoDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/locacoes")
public class LocacaoResource {


    @PostMapping
    public void efetuaLocacao(@RequestBody LocacaoDTO locacaoDTO) {
        System.out.println(locacaoDTO);
    }
}
