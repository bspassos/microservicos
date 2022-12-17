package br.edu.infnet.locacao.resources.dto;

public class EquipamentoDTO {

    private Integer codigo;

    private Integer quantidade;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "EquipamentoDTO{" +
                "codigo=" + codigo +
                ", quantidade=" + quantidade +
                '}';
    }
}
