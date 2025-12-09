package dto;

public class ClientResponseDTO {
    private Long id;
    private String name;
    private String cpf;
    private String email;
    private Long accountId;
    private String accountNumber;

    public ClientResponseDTO() {}

    public ClientResponseDTO(Long id, String name, String cpf, String email, 
                            Long accountId, String accountNumber) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.accountId = accountId;
        this.accountNumber = accountNumber;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public Long getAccountId() { return accountId; }
    public void setAccountId(Long accountId) { this.accountId = accountId; }
    
    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
}