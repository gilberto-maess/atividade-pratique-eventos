package com.pratique.domain.usuarios;

import com.pratique.domain.eventos.EventoException;

public enum Tipo {
    ADMIN("Admin"),
    COMUM("Comum");

    private final String value;

    private Tipo(String value) {
        this.value = value;
    }

    private String getValue() {
        return this.value;
    }

    public static boolean isValid(String value) {
        for (Tipo tipo : Tipo.values()) {
            if (tipo.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }

    public static Tipo fromString(String value) throws EventoException {
        for (Tipo tipo : Tipo.values()) {
            if (tipo.getValue().equalsIgnoreCase(value)) {
                return tipo;
            }
        }
        throw new EventoException("Tipo informado não existe: " + value);
    }

}
