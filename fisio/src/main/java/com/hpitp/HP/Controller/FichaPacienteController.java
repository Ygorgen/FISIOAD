package com.hpitp.HP.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hpitp.HP.Model.FichaPaciente;
import com.hpitp.HP.Service.FichaPacienteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/ficha-paciente")
public class FichaPacienteController {

    @Autowired
    private FichaPacienteService fichaPacienteService;

    @PostMapping
public ResponseEntity<?> cadastrarFichas(@Valid @RequestBody List<FichaPaciente> fichasPaciente) {
    List<FichaPaciente> fichasSalvas = fichaPacienteService.cadastrarFichasPaciente(fichasPaciente);
    
    if (fichasSalvas.isEmpty()) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nenhuma ficha foi cadastrada. Verifique os erros.");
    }
    
    return ResponseEntity.status(HttpStatus.CREATED).body(fichasSalvas);
}


    @PostMapping("/cadastrar")
    public ResponseEntity<FichaPaciente> cadastroFicha(@RequestBody FichaPaciente fichaPaciente){
        FichaPaciente fichasalva = fichaPacienteService.cadastrarFichaPaciente(fichaPaciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(fichasalva);
    }

    @GetMapping
    public ResponseEntity <List<FichaPaciente>> findAll(){
        List <FichaPaciente> fichaPacientes = fichaPacienteService.findAll();
        return ResponseEntity.ok(fichaPacientes);

    }
}
