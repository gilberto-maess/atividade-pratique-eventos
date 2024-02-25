package com.pratique.domain.eventos;

public enum Categoria {
	FESTA("Festa"),
	ESPORTE("Esporte"),
	SHOW("Show"),
	FEIRA("Feira"),
	BOOTCAMP("Bootcamp"),
	TREINAMENTO("Treinamento"),
	CONGRESSO("Congresso"),
	OUTROS("Outros");
	
	private final String value;
	
	private Categoria(String value) {
		this.value = value;
	}
	
	private String getValue() {
		return this.value;
	}
	
	public static boolean isValid(String value) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }
	
	public static Categoria fromString(String value) throws EventoException {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.getValue().equalsIgnoreCase(value)) {
                return categoria;
            }
        }
        throw new EventoException("Categoria informada não existe: " + value);
    }
	
}
