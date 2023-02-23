package com.example.dongmyung.user;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum UserRole {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER"),
    GUEST("ROLE_GUEST");

    private String value;

    UserRole(String value) {
        this.value = value;
    }
}