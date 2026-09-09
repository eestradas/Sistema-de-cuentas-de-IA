package es.upm.etsisi.poo;

public interface Billable {
    double getCost();
    void markAsBilled();
    boolean isBilled();
}
