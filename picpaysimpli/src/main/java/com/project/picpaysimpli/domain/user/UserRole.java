package com.project.picpaysimpli.domain.user;

public enum UserRole {

    USER("user"),

    LOJISTA("lojista");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public  String getRole() {
        return role;
    }
}
