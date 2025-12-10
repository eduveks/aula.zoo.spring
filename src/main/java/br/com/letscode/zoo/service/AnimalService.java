package br.com.letscode.zoo.service;

import br.com.letscode.zoo.exception.NotFoundException;
import br.com.letscode.zoo.model.Animal;
import br.com.letscode.zoo.repository.AnimalRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AnimalService {
    private AnimalRepository animalRepository;

    private CategoryService categoryService;

    public Animal findByUid(String uid) throws NotFoundException {
        List<Animal> animals = animalRepository.findByUid(uid);
        var dbAnimal = animals.stream().findFirst();
        return dbAnimal.orElseThrow(() -> new NotFoundException("Animal "+ uid +" não encontrado."));
    }

    public void create(Animal animal) throws NotFoundException {
        animal.setUid(UUID.randomUUID().toString());
        var category = categoryService.findByUid(animal.getCategory().getUid());
        animal.setCategory(category);
        animalRepository.saveAndFlush(animal);
    }

    public void update(Animal animal) throws NotFoundException {
        Animal animalDB = findByUid(animal.getUid());
        animalDB.setName(animal.getName());
        animalDB.setAge(animal.getAge());
        var category = categoryService.findByUid(animal.getCategory().getUid());
        animalDB.setCategory(category);
        animalRepository.save(animalDB);
    }

    public void remove(String uid) throws NotFoundException {
        Animal animalDB = findByUid(uid);
        animalRepository.delete(animalDB);
    }

    public List<Animal> findAllByName(String name, Pageable p) {
        return animalRepository.findAllByName(name.toLowerCase(), p);
    }
}
