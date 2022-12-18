package br.edu.infnet.catalogo.modelo.entidades;

import javax.persistence.*;


@Entity
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private String nome;
    private float mensalidade;


    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
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
        return "Equipamento{" +
                "id=" + codigo +
                ", nome='" + nome + '\'' +
                ", mensalidade=" + mensalidade +
                '}';
    }
}