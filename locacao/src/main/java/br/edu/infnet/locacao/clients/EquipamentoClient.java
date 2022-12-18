package br.edu.infnet.locacao.clients;

import br.edu.infnet.locacao.resources.dto.EquipamentoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("catalogo")
public interface EquipamentoClient {

    @RequestMapping("/equipamentos")
    List<EquipamentoDTO> getEquipamentos();

}
