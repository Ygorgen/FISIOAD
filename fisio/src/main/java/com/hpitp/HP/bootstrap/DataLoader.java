package com.hpitp.HP.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.hpitp.HP.Model.Setor;
import com.hpitp.HP.Repository.SetorRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final SetorRepository setorRepository;

    public DataLoader(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (setorRepository.count() == 0) {
            setorRepository.save(new Setor("CTI GERAL"));
            setorRepository.save(new Setor("CTI CARDIO"));
            setorRepository.save(new Setor("CTI PÓS OP"));
            setorRepository.save(new Setor("CTI PED"));
            setorRepository.save(new Setor("UI"));
            setorRepository.save(new Setor("EMERGENCIA"));
            setorRepository.save(new Setor("UI PED"));
        }
    }
}