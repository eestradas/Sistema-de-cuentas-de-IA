package es.upm.etsisi.poo.ia;

public class DummyIA implements IAEngine{
    @Override
    public String query(String text) {
        return "Hola Mundo";
    }
}
