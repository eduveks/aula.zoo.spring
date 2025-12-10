package br.com.letscode.zoo.repository;

import br.com.letscode.zoo.model.Animal;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    @Query("SELECT a FROM Animal a JOIN FETCH a.category c WHERE a.uid = :uid")
    List<Animal> findByUid(@Param("uid") String uid);

    @Query("SELECT a FROM Animal a JOIN FETCH a.category c WHERE a.name LIKE %:name%")
    List<Animal> findAllByName(String name, Pageable p);
}
