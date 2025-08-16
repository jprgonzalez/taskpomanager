package br.com.jprgonzalez.taskpomanager.user.dto;

import lombok.Data;

@Data
public class UserCreationRequestDTO {
    private Long id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String role;
    private Long teamId;
    private Boolean active;

    public UserCreationRequestDTO(String name, String username, String password, String email, String role, Long teamId, Boolean active) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.teamId = teamId;
        this.active = active;
    }

}
