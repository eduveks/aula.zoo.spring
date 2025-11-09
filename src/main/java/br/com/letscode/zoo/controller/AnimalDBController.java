package br.com.letscode.zoo.controller;

import br.com.letscode.zoo.dto.AnimalDTO;
import br.com.letscode.zoo.dto.FactoryDTO;
import br.com.letscode.zoo.exception.NotFoundException;
import br.com.letscode.zoo.service.AnimalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/animal-db")
@AllArgsConstructor
public class AnimalDBController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AnimalDBController.class);
    private AnimalService animalService;

    @Operation(summary = "Obtém os dados dos Animais", description = "Retorna o detalhe de um animal.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dados do animal correspondente ao UID."),
            @ApiResponse(responseCode = "404", description = "Nenhum animal foi encontrado com o UID.")
    })
    @GetMapping("/{uid}")
    public ResponseEntity<AnimalDTO> getByUid(@PathVariable(value = "uid") String uid) {
        try {
            return ResponseEntity.ok(FactoryDTO.entityToDTO(
                    animalService.findByUid(uid)
            ));
        } catch (NotFoundException e) {
            LOGGER.warn(e.toString());
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody AnimalDTO animalDTO) {
        var animal = FactoryDTO.dtoToEntity(animalDTO);
        try {
            animalService.create(animal);
            return ResponseEntity.ok(FactoryDTO.entityToDTO(animal));
        } catch (NotFoundException e) {
            LOGGER.warn(e.toString());
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody AnimalDTO animalDTO) {
        var animal = FactoryDTO.dtoToEntity(animalDTO);
        try {
            animalService.update(animal);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            LOGGER.warn(e.toString());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<?> remove(@RequestParam("uid") String uid) {
        try {
            animalService.remove(uid);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            LOGGER.warn(e.toString());
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String>handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(e -> {
            String fieldName = ((FieldError)e).getField();
            String errorMessage = e.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
