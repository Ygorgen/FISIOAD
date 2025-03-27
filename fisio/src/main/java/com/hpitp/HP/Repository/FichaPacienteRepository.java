package com.hpitp.HP.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hpitp.HP.Model.FichaPaciente;
import com.hpitp.HP.Model.Setor;


public interface FichaPacienteRepository extends JpaRepository<FichaPaciente, Long> {
    Optional<FichaPaciente> findByAtendimentoAndSetor(Integer atendimento, Setor setor);
}
