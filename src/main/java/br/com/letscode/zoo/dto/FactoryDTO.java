package br.com.letscode.zoo.dto;

import br.com.letscode.zoo.model.Animal;
import br.com.letscode.zoo.model.Category;

import java.util.List;
import java.util.stream.Collectors;

public class FactoryDTO {

    public static CategoryDTO entityToDTO(Category category) {
        if (category == null) {
            return null;
        }
        return new CategoryDTO(category.getUid(), category.getName());
    }

    public static AnimalDTO entityToDTO(Animal animal) {
        return new AnimalDTO(
                animal.getUid(),
                animal.getName(),
                animal.getAge(),
                null,
                null,
                entityToDTO(animal.getCategory())
        );
    }

    public static List<AnimalDTO> entityToDTO(List<Animal> animals) {
        return animals.stream().map(FactoryDTO::entityToDTO)
                .toList();
    }

    public static Category dtoToEntity(CategoryDTO categoryDTO) {
        if (categoryDTO == null) {
            return null;
        }
        var category = new Category();
        category.setUid(categoryDTO.getUid());
        category.setName(categoryDTO.getName());
        return category;
    }

    public static Animal dtoToEntity(AnimalDTO animalDTO) {
        var animal = new Animal();
        animal.setUid(animalDTO.getUid());
        animal.setName(animalDTO.getName());
        animal.setAge(animalDTO.getAge());
        animal.setCategory(dtoToEntity(animalDTO.getCategory()));
        return animal;
    }

    public static List<Animal> dtoToEntity(List<AnimalDTO> animals) {
        return animals.stream().map(FactoryDTO::dtoToEntity)
                .toList();
    }
}
