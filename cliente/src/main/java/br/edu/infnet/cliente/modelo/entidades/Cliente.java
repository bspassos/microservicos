package br.edu.infnet.cliente.modelo.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cliente {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private String email;
    private String nome;
    private String endereco;


    public Cliente() {

    }

    public Cliente(Long codigo, String email, String nome, String endereco) {
        super();
        this.codigo = codigo;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
    }


    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
