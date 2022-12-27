package br.edu.infnet.locacao.clients;

import br.edu.infnet.locacao.resources.dto.EquipamentoCatalogoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("catalogo")
public interface EquipamentoClient {

    @PostMapping("/equipamentos/search")
    ResponseEntity<List<EquipamentoCatalogoDTO>> getEquipamentos(List<EquipamentoCatalogoDTO> equipamentos);

}
