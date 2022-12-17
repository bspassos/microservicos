package br.edu.infnet.locacao.resources.dto;

import java.util.List;

public class LocacaoDTO {
    private Integer clienteId;

    private List<EquipamentoDTO> equipamentos;

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public List<EquipamentoDTO> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<EquipamentoDTO> equipamentos) {
        this.equipamentos = equipamentos;
    }

    @Override
    public String toString() {
        return "LocacaoDTO{" +
                "clienteId=" + clienteId +
                ", equipamentos=" + equipamentos +
                '}';
    }
}
