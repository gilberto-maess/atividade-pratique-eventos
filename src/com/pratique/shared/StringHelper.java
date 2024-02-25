package com.pratique.shared;

public class StringHelper {

	private StringHelper() {}

	public static boolean isNullOrEmpty(String str) {
		return str == null || str.isEmpty();
	}
	
	public static boolean isValidEmail(String str) {
		if (isNullOrEmpty(str)) {
			return false;
		}
		
		String[] vetor = str.split("@");
		if (vetor.length != 2) {
			return false;
		}
		
		if (vetor[0].trim().length() == 0) {
			return false;
		}
		
		if (vetor[1].trim().length() == 0) {
			return false;
		}
		
		return true;
	}
}