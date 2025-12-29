package com.vilelo.sdjpa_inheritance.bootstrap;

import com.vilelo.sdjpa_inheritance.joined.ElectricGuitar;
import com.vilelo.sdjpa_inheritance.repositories.ElectricGuitarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    @Autowired
    ElectricGuitarRepository electricGuitarRepository;

    @Override
    public void run(String... args) throws Exception {

        ElectricGuitar eg = new ElectricGuitar();
        eg.setNumberOfStrings(6);
        eg.setNumberOfPickups(2);

        electricGuitarRepository.save(eg);

    }
}
