package com.pratique.shared;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringHelper {

	private StringHelper() {
	}

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

	public static String toSha256(String str) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(str.getBytes());
		return toHexString(hash);
	}

	private static String toHexString(byte[] hash) {
		StringBuilder hexString = new StringBuilder(2 * hash.length);
		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}
}