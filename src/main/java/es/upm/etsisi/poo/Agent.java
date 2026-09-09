package es.upm.etsisi.poo;

import java.util.List;

public class Agent {
    private final User user;
    private final int id;

    public Agent(User user, int id) {
        this.user = user;
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public int getId() {
        return id;
    }

    public String buildResponse(List<String> iaResponses, int totalInputWords) {
        int totalVowels = iaResponses.stream()
                .mapToInt(Agent::countVowels)
                .sum();
        int x = totalVowels + totalInputWords;
        return "El agente piensa que son " + x + " coeficientes";
    }

    private static int countVowels(String text) {
        int count = 0;
        for (char c : text.toLowerCase().toCharArray()) {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                count++;
            }
        }
        return count;
    }
}
