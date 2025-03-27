package com.hpitp.HP.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name= "tb_fichaPaciente")
@Entity
public class FichaPaciente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String nome;
    
    private Integer idade;
    
    private Integer atendimento;

    private Integer leito;
    
    private String plano;

    private String diagnostico;

    @ManyToOne
    @JsonIgnoreProperties("fichaPacientes")
    private Setor setor;

}
