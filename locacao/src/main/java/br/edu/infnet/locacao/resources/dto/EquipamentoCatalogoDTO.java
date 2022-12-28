package br.edu.infnet.locacao.resources.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class EquipamentoCatalogoDTO {

    private Integer id;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String nome;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private float mensalidade;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", mensalidade=" + mensalidade +
                '}';
    }
}
