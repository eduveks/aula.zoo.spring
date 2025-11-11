package br.com.letscode.zoo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AnimalDTO {
    private String uid;
    @Schema(description = "Nome do Animal", example = "Leão")
    @NotBlank(message="O nome é obrigatório.")
    @Size(min = 3, message="Nome muito curto.")
    private String name;

    @Schema(description = "Idade do Animal", example = "15")
    @Min(value = 1, message = "Idade inválida.")
    @Max(value = 100, message = "Idade inválida.")
    private int age;

    @NotBlank(message="O e-mail é obrigatório.")
    @Email(message = "E-mail inválido.")
    private String email;

    @NotBlank(message="O CEP é obrigatório.")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP inválido.")
    private String cep;
    private CategoryDTO category;
}
