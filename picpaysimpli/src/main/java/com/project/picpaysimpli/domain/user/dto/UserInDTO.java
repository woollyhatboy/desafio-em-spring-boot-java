package com.project.picpaysimpli.domain.user.dto;

import com.project.picpaysimpli.domain.user.UserRole;
import jakarta.validation.constraints.NotBlank;

public record UserInDTO(@NotBlank String name, @NotBlank String email,@NotBlank  String password,@NotBlank  String cpfCnpj, UserRole role) {
}
