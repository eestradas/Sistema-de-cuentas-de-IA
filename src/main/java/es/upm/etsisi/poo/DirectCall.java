package es.upm.etsisi.poo;

public class DirectCall implements Billable{

    private final String text;
    private final double cost;
    private boolean billed;

    public DirectCall(String text, double cost) {
        this.text = text;
        this.cost = cost;
        this.billed = false;
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

    @Override
    public double getCost() {
        return cost;
    }
}
