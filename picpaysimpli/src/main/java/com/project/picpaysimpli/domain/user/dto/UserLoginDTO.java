package com.project.picpaysimpli.domain.user.dto;

import jakarta.validation.constraints.NotBlank;

public record UserLoginDTO(@NotBlank String email,@NotBlank String password) {
}
