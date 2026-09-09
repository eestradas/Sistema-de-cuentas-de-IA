package es.upm.etsisi.poo.command;

import es.upm.etsisi.poo.controller.CallController;

public class SendCallCommand implements Command{

    private final CallController callController;
    private final String dni;
    private final String text;

    public SendCallCommand(CallController callController, String dni, String text) {
        this.callController = callController;
        this.dni = dni;
        this.text = text;
    }

    @Override
    public void execute() {
        callController.sendCall(dni, text);
    }
}
