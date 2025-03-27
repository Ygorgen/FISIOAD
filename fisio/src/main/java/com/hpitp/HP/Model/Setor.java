package com.hpitp.HP.Model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Table(name = "tb_setor")
@Entity
public class Setor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany(fetch = FetchType.LAZY ,mappedBy = "setor" , cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("setor")
    private List<FichaPaciente> fichaPacientes;

    public Setor() {
    }

    public Setor(String nome) {
        this.nome = nome;
    }
}
