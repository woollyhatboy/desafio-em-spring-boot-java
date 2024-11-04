package com.project.picpaysimpli.domain.user.dto;

import com.project.picpaysimpli.domain.user.User;

public record InformationToTransferDTO(Double money, Long payer, Long receiver) {
}
