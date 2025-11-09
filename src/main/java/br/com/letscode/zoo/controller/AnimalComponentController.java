package br.com.letscode.zoo.controller;

import br.com.letscode.zoo.dto.AnimalDTO;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/animal-component")
@AllArgsConstructor
public class AnimalComponentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnimalComponentController.class);

    private static List<AnimalDTO> dbList = Collections.synchronizedList(
            new ArrayList() {{
                    add(new AnimalDTO(UUID.randomUUID().toString(), "Gato", 10, null, null, null));
                    add(new AnimalDTO(UUID.randomUUID().toString(), "Cachorro", 20, null, null, null));
                    add(new AnimalDTO(UUID.randomUUID().toString(), "Elefante", 30, null, null, null));
            }}
    );

    @GetMapping("/list")
    public synchronized ResponseEntity<Collection<AnimalDTO>> list(
            @RequestParam(defaultValue = "0") int page
    ) {
        LOGGER.info("Processa Lista...");
        try {
            "".substring(10);
        } catch (Exception e) {
            LOGGER.debug("Falha no substring...", e);
        }
        List<AnimalDTO> list = page == 0 ? dbList : dbList.stream().skip(page).limit(1).collect(Collectors.toList());
        return ResponseEntity
                .ok()
                .header("List-Size", Integer.toString(dbList.size()))
                .body(list);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody AnimalDTO animal) {
        ResponseEntity.noContent().build();
        dbList.add(animal);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestParam("id") int index, @RequestBody AnimalDTO animal) {
        List<AnimalDTO> myList = dbList;
        myList.remove(index);
        myList.add(animal);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam("id") int index) {
        List<AnimalDTO> myList = dbList;
        myList.remove(index);
        return ResponseEntity.ok().build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(e -> {
            String fieldName = ((FieldError)e).getField();
            String errorMessage = e.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
