package br.edu.infnet.locacao.resources.dto;

import java.time.LocalDateTime;
import java.util.List;

public class LocacaoResponseDTO {

    private Integer id;
    private LocalDateTime data;
    private ClienteDTO cliente;
    private List<EquipamentoCatalogoDTO> equipamentosCatalogoDTO;

    public LocacaoResponseDTO() {
    }

    public LocacaoResponseDTO(ClienteDTO cliente, List<EquipamentoCatalogoDTO> equipamentosCatalogoDTO) {
        super();
        this.cliente = cliente;
        this.equipamentosCatalogoDTO = equipamentosCatalogoDTO;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public List<EquipamentoCatalogoDTO> getEquipamentosCatalogoDTO() {
        return equipamentosCatalogoDTO;
    }


    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public LocalDateTime getData() {
        return data;
    }
}
