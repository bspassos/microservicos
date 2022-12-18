package br.edu.infnet.locacao.resources.dto;

public class EquipamentoDTO {

    private Integer codigo;

    private Integer quantidade;

    private String nome;

    private float mensalidade;

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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getMensalidade() {
        return mensalidade;
    }

    public void setMensalidade(float mensalidade) {
        this.mensalidade = mensalidade;
    }

    @Override
    public String toString() {
        return "EquipamentoDTO{" +
                "codigo=" + codigo +
                ", quantidade=" + quantidade +
                ", nome='" + nome + '\'' +
                ", mensalidade=" + mensalidade +
                '}';
    }
}
