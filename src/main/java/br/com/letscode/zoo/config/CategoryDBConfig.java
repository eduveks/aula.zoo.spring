package br.com.letscode.zoo.config;

import br.com.letscode.zoo.exception.NotFoundException;
import br.com.letscode.zoo.model.Animal;
import br.com.letscode.zoo.model.Category;
import br.com.letscode.zoo.repository.AnimalRepository;
import br.com.letscode.zoo.repository.CategoryRepository;
import br.com.letscode.zoo.service.AnimalService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import java.util.UUID;

@Configuration
@AllArgsConstructor
public class CategoryDBConfig {
    private CategoryRepository categoryRepository;

    private AnimalService animalService;

    @PostConstruct
    public void initDB() throws NotFoundException {
        Category category1 = new Category();
        category1.setUid("b59c8b8b-2326-4f1d-bacf-dc45f30b13fa");
        category1.setName("Peixe");
        categoryRepository.saveAndFlush(category1);

        Category category2 = new Category();
        category2.setUid("912d5a73-cd51-4ca3-b293-6c1626b63d9c");
        category2.setName("Mamífero");
        categoryRepository.saveAndFlush(category2);

        Category category3 = new Category();
        category3.setUid("6c3293d0-f536-4fc3-9a3d-eac45c086325");
        category3.setName("Ave");
        categoryRepository.saveAndFlush(category3);

        Animal animal1 = new Animal();
        animal1.setUid(UUID.randomUUID().toString());
        animal1.setName("Sardinha");
        animal1.setAge(15);
        Category categoryAnimal1 = new Category();
        categoryAnimal1.setUid("b59c8b8b-2326-4f1d-bacf-dc45f30b13fa");
        animal1.setCategory(categoryAnimal1);
        animalService.create(animal1);

        Animal animal2 = new Animal();
        animal2.setUid(UUID.randomUUID().toString());
        animal2.setName("Zebra");
        animal2.setAge(40);
        Category categoryAnimal2 = new Category();
        categoryAnimal2.setUid("912d5a73-cd51-4ca3-b293-6c1626b63d9c");
        animal2.setCategory(categoryAnimal2);
        animalService.create(animal2);

        Animal animal21 = new Animal();
        animal21.setUid(UUID.randomUUID().toString());
        animal21.setName("Girafa");
        animal21.setAge(65);
        Category categoryAnimal21 = new Category();
        categoryAnimal21.setUid("912d5a73-cd51-4ca3-b293-6c1626b63d9c");
        animal21.setCategory(categoryAnimal21);
        animalService.create(animal21);

        Animal animal3 = new Animal();
        animal3.setUid(UUID.randomUUID().toString());
        animal3.setCategory(category3);
        Category categoryAnimal3 = new Category();
        categoryAnimal3.setUid("6c3293d0-f536-4fc3-9a3d-eac45c086325");
        animal3.setCategory(categoryAnimal3);
        animal3.setName("Guara");
        animal3.setAge(50);
        animalService.create(animal3);
    }

}
