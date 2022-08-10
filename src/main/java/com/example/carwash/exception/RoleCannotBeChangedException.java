package com.example.carwash.exception;

import com.example.carwash.model.Role;

public class RoleCannotBeChangedException extends RuntimeException {
    public RoleCannotBeChangedException(Long id, Role currentRole, Role role) {
        super(String.format("User with id = %d has role '%s' and it can not be changed to '%s'",
                id, currentRole.toString(), role.toString()));
    }
}
