package dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class ClientRequestDTO {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "CPF is mandatory")
    @Pattern(regexp = "\\d{11}", message = "CPF must have 11 digits")
    private String cpf;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email")
    private String email;

    public ClientRequestDTO() {}

    public ClientRequestDTO(String name, String cpf, String email) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
    }

    // Getters e Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}