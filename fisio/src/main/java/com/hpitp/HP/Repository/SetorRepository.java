package com.hpitp.HP.Repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hpitp.HP.Model.Setor;

public interface SetorRepository extends JpaRepository<Setor, Long> {
    Optional<Setor> findByNome(String nome);

}
