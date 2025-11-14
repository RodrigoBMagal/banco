package dto;

import jakarta.validation.constraints.NotBlank;

public class ClientRequestDTO {

    @NotBlank(message = "Name is mandatory")
    private String name;

    public ClientRequestDTO(){
    }

    public ClientRequestDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
    

