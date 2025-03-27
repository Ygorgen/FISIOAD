package com.hpitp.HP.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hpitp.HP.Model.FichaPaciente;
import com.hpitp.HP.Model.Setor;
import com.hpitp.HP.Repository.FichaPacienteRepository;
import com.hpitp.HP.Repository.SetorRepository;

@Service
public class FichaPacienteService {

    @Autowired
    private FichaPacienteRepository fichaPacienteRepository;

    @Autowired
    private SetorRepository setorRepository;

    public List<FichaPaciente> cadastrarFichasPaciente(List<FichaPaciente> fichasPaciente) {
        List<FichaPaciente> fichasSalvas = new ArrayList<>();
        List<String> erros = new ArrayList<>(); // Lista para coletar os erros
    
        for (FichaPaciente fichaPaciente : fichasPaciente) {
            try {
                Setor setor = determinarSetorPorLeito(fichaPaciente.getLeito());
    
                if (setor == null) {
                    erros.add("Leito " + fichaPaciente.getLeito() + " não corresponde a nenhum setor válido.");
                    continue; // Pula este paciente e vai para o próximo
                }
    
                fichaPaciente.setSetor(setor);
    
                Optional<FichaPaciente> fichaExistente = fichaPacienteRepository
                        .findByAtendimentoAndSetor(fichaPaciente.getAtendimento(), fichaPaciente.getSetor());
    
                if (fichaExistente.isEmpty()) {
                    fichasSalvas.add(fichaPacienteRepository.save(fichaPaciente));
                } else {
                    erros.add("Atendimento " + fichaPaciente.getAtendimento() + " já existe no setor " + setor.getNome());
                }
            } catch (Exception e) {
                erros.add("Erro ao cadastrar paciente " + fichaPaciente.getNome() + ": " + e.getMessage());
            }
        }
    
        if (!erros.isEmpty()) {
            System.err.println("Erros encontrados: " + erros);
        }
    
        return fichasSalvas;
    }
    

    public FichaPaciente cadastrarFichaPaciente(FichaPaciente fichaPaciente) {
        try {
            // Determina o setor com base no leito informado
            Setor setor = determinarSetorPorLeito(fichaPaciente.getLeito());

            if (setor == null) {
                throw new IllegalArgumentException("Leito não corresponde a nenhum setor válido.");
            }

            // Define o setor na ficha do paciente
            fichaPaciente.setSetor(setor);

            // Verifica se já existe uma ficha com o mesmo atendimento e setor
            Optional<FichaPaciente> fichaExistente = fichaPacienteRepository
                    .findByAtendimentoAndSetor(fichaPaciente.getAtendimento(), fichaPaciente.getSetor());

            if (fichaExistente.isEmpty()) {
                // Se não existir, salva a nova ficha
                return fichaPacienteRepository.save(fichaPaciente);
            } else {
                // Se existir, lança uma exceção
                throw new IllegalArgumentException("O número de atendimento já existe neste setor.");
            }
        } catch (Exception e) {
            // Registra o erro (por exemplo, em um log)
            System.err.println("Erro ao cadastrar ficha do paciente: " + e.getMessage());
            throw e; // Opcional: repropagar a exceção, caso necessário
        }

    }

    private Setor determinarSetorPorLeito(Integer leito) {
        if (leito == null) {
            throw new IllegalArgumentException("O leito informado é nulo.");
        }
    
        if (leito >= 201 && leito <= 210) {
            return setorRepository.findByNome("CTI Cardio").orElseThrow(
                    () -> new IllegalArgumentException("Setor CTI Cardio não encontrado."));
        } else if (leito >= 211 && leito <= 220) {
            return setorRepository.findByNome("CTI Pós Op").orElseThrow(
                    () -> new IllegalArgumentException("Setor CTI Pós Op não encontrado."));
        } else if (leito >= 221 && leito <= 250) {
            return setorRepository.findByNome("CTI Geral").orElseThrow(
                    () -> new IllegalArgumentException("Setor CTI Geral não encontrado."));
        } else if (leito >= 301 && leito <= 400) {
            return setorRepository.findByNome("UI").orElseThrow(
                    () -> new IllegalArgumentException("Setor UI não encontrado."));
        } else if (leito >= 109 && leito <= 113) {
            return setorRepository.findByNome("UI PED").orElseThrow(
                    () -> new IllegalArgumentException("Setor UI PED não encontrado."));
        } else if (leito >= 101 && leito <= 108) {
            return setorRepository.findByNome("CTI PED").orElseThrow(
                    () -> new IllegalArgumentException("Setor CTI PED não encontrado."));
        } else {
            throw new IllegalArgumentException("Leito não corresponde a nenhum setor.");
        }
    }
    
    public List<FichaPaciente> findAll() {
        return fichaPacienteRepository.findAll();
    }
}