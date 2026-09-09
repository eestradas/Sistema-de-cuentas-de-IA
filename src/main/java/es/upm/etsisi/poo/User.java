package es.upm.etsisi.poo;

import java.util.ArrayList;
import java.util.List;

public abstract class User {

    private static final int FREE_WORDS_PER_DAY = 5;

    private final String dni;
    private final String name;
    private final String password;

    private int freeWordsUsedToday;
    private List<Agent> agents;


    public User(String dni, String name, String password) {
        this.dni = dni;
        this.name = name;
        this.password = password;
        this.freeWordsUsedToday = 0;
        this.agents = new ArrayList<>();
    }

    public String getDni() {
        return dni;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getFreeWordsUsedToday() {
        return freeWordsUsedToday;
    }

    public int getFreeWordsRemaining(){
        return Math.max(0,FREE_WORDS_PER_DAY - freeWordsUsedToday);
    }

    public void consumeFreeWords(int words){
        this.freeWordsUsedToday += words;
    }

    public void addAgent(Agent agent){
        agents.add(agent);
    }

    public List<Agent> getAgents(){
        return agents;
    }

    public boolean hasAgent(int agentId){
        return agents.stream().anyMatch(a -> a.getId() == agentId);
    }

    public Agent getAgent(int agentId) {
        return agents.stream()
                .filter(a -> a.getId() == agentId)
                .findFirst()
                .orElse(null);
    }

    public abstract boolean canExecute(int wordCount);

    public abstract double registerUsage(int wordCount);

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[dni=" + dni + ", nombre=" + name + "]";
    }
}
