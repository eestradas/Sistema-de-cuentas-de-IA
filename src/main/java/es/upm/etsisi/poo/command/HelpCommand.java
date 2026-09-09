package es.upm.etsisi.poo.command;

import es.upm.etsisi.poo.Messages;

public class HelpCommand implements Command{
    @Override
    public void execute() {
        System.out.println(Messages.HELP);
    }
}
