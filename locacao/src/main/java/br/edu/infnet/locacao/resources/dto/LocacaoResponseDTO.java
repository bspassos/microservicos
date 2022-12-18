package br.edu.infnet.locacao.resources.dto;

import java.util.List;

public class LocacaoResponseDTO {

    private ClienteDTO cliente;
    private List<EquipamentoCatalogoDTO> equipamentosCatalogoDTO;

    public LocacaoResponseDTO() {
    }

    public LocacaoResponseDTO(ClienteDTO cliente, List<EquipamentoCatalogoDTO> equipamentosCatalogoDTO) {
        super();
        this.cliente = cliente;
        this.equipamentosCatalogoDTO = equipamentosCatalogoDTO;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public List<EquipamentoCatalogoDTO> getEquipamentosCatalogoDTO() {
        return equipamentosCatalogoDTO;
    }
}
