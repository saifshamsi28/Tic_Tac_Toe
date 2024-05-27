package com.example.tic_tac_toe;

public class NameFormatter {
    public static String formatName(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        StringBuilder camelCaseString = new StringBuilder();
        boolean isNextUpperCase = true;

        for (char c : input.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                isNextUpperCase = true;
                camelCaseString.append(" ");
            } else if (isNextUpperCase) {
                camelCaseString.append(Character.toUpperCase(c));
                isNextUpperCase = false;
            } else {
                camelCaseString.append(Character.toLowerCase(c));
            }
        }

        return camelCaseString.toString();
    }
}
