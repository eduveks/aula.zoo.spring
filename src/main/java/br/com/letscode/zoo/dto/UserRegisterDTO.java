package br.com.letscode.zoo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRegisterDTO {

    @NotBlank(message="O usuário é obrigatório.")
    @Pattern(regexp = "[a-z0-1]+", message = "Usuário inválido.")
    private String username;

    @NotBlank(message="O nome é obrigatório.")
    private String name;

    @NotBlank(message="O e-mail é obrigatório.")
    @Email(message = "E-mail inválido.")
    private String email;

    @NotBlank(message="A senha é obrigatório.")
    @Size(min = 8, message="Senha muito curta.")
    private String password;
}
