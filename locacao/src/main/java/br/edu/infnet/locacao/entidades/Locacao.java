package br.edu.infnet.locacao.entidades;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Locacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime data;
    private int meses;
    private Integer idCliente;
    @ElementCollection
    private List<Integer> idEquipamento;

    public Locacao() {

    }

    public Locacao(int meses, Integer idCliente, List<Integer> idEquipamento) {
        this.data = LocalDateTime.now();
        this.meses = meses;
        this.idCliente = idCliente;
        this.idEquipamento = idEquipamento;
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public int getMeses() {
        return meses;
    }

    public void setMeses(int meses) {
        this.meses = meses;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public List<Integer> getIdEquipamento() {
        return idEquipamento;
    }

    public void setIdEquipamento(List<Integer> idEquipamento) {
        this.idEquipamento = idEquipamento;
    }

    @Override
    public String toString() {
        return "Locacao{" +
                "id=" + id +
                ", data=" + data +
                ", meses=" + meses +
                ", idCliente=" + idCliente +
                ", idEquipamento=" + idEquipamento +
                '}';
    }
}
