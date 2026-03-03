package dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequestDTO {
    @NotBlank(message = "Name is mandatory")
    private String name;
    
    @NotBlank(message = "CPF is mandatory")
    @Pattern(regexp = "\\d{11}", message = "CPF must have 11 digits")
    private String cpf;
    
    @Email(message = "Invalid email")
    private String email;
}