package br.com.letscode.zoo.config;

import br.com.letscode.zoo.model.Animal;
import br.com.letscode.zoo.model.Category;
import br.com.letscode.zoo.repository.AnimalRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import java.util.UUID;

@Configuration
@AllArgsConstructor
public class AnimalDBConfig {
    private AnimalRepository animalRepository;

    @PostConstruct
    public void initDB() {
        Animal animal1 = new Animal();
        animal1.setUid(UUID.randomUUID().toString());
        animal1.setName("Peixe Palhaço");
        animal1.setAge(10);
        animalRepository.saveAndFlush(animal1);

        Animal animal2 = new Animal();
        animal2.setUid(UUID.randomUUID().toString());
        animal2.setName("Elefante");
        animal2.setAge(90);
        animalRepository.saveAndFlush(animal2);

        Animal animal3 = new Animal();
        animal3.setUid(UUID.randomUUID().toString());
        animal3.setName("Urubu");
        animal3.setAge(60);
        animalRepository.saveAndFlush(animal3);
    }
}
