package br.edu.infnet.locacao.resources.dto;

public class ClienteDTO {

    private Integer id;
    private String email;
    private String nome;
    private String endereco;

    public ClienteDTO() {

    }

    public ClienteDTO(Integer id, String email, String nome, String endereco) {
        super();
        this.id = id;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "ClienteDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                '}';
    }
}
