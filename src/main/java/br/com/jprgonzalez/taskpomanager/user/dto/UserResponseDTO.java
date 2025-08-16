package br.com.jprgonzalez.taskpomanager.user.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserResponseDTO {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String role;
    private Long teamId;
    private Boolean active;
    private LocalDateTime createdAt;
    private Long createdByUserId;
    private LocalDateTime updatedAt;
    private Long updatedByUserId;

    public UserResponseDTO(Long id, String name, String username, String email, String role, Long teamId, Boolean active, LocalDateTime createdAt, Long createdByUserId, LocalDateTime updatedAt, Long updatedByUserId) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.role = role;
        this.teamId = teamId;
        this.active = active;
        this.createdAt = createdAt;
        this.createdByUserId = createdByUserId;
        this.updatedAt = updatedAt;
        this.updatedByUserId = updatedByUserId;
    }

}
