package es.upm.etsisi.poo;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilities {

    private static final String DNI_LETTERS = "TRWAGMYFPDXBNJZSQVHLCKE";

    private Utilities(){};

    public static boolean isValidDni(String dni) {
        if (dni == null || !dni.matches("\\d{8}[A-Z]")) return false;
        int number = Integer.parseInt(dni.substring(0, 8));
        char expected = DNI_LETTERS.charAt(number % 23);
        return dni.charAt(8) == expected;
    }

    public static boolean isValidAccount(String numeroCuenta) {
        return numeroCuenta != null && numeroCuenta.matches("\\d{22}");
    }

    public static int countWords(String text) {
        if (text == null || text.isBlank()) return 0;
        return text.trim().split("\\s+").length;
    }

    public static String[] splitLine(String line) {
        List<String> tokens = new ArrayList<>();
        Matcher m = Pattern.compile("\"([^\"]*)\"|\\S+").matcher(line);
        while (m.find()) {
            tokens.add(m.group(1) != null ? m.group(1) : m.group());
        }
        return tokens.toArray(new String[0]);
    }

    public static int parseAgentId(String token) {
        try {
            return Integer.parseInt(token);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
