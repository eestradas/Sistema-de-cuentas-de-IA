package es.upm.etsisi.poo;

public class AgentExecution implements Billable{
    private final String text;
    private final double cost;
    private boolean billed;
    private final int id;

    public AgentExecution(String text, double cost, int id) {
        this.text = text;
        this.cost = cost;
        this.billed = false;
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public boolean isBilled() {
        return billed;
    }

    public void markAsBilled() {
        this.billed = true;
    }

    public int getId() {
        return id;
    }

    @Override
    public double getCost() {
        return cost;
    }
}