package es.upm.etsisi.poo;

public class NormalUser extends User{

    public NormalUser(String dni, String nombre, String password) {
        super(dni, nombre, password);
    }

    @Override
    public boolean canExecute(int wordCount) {
        return getFreeWordsRemaining() >= wordCount;
    }

    @Override
    public double registerUsage(int wordCount) {
        consumeFreeWords(wordCount);
        return 0.0;
    }
}
