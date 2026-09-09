package es.upm.etsisi.poo.app;

import es.upm.etsisi.poo.command.CommandParser;
import es.upm.etsisi.poo.controller.AgentController;
import es.upm.etsisi.poo.controller.BillController;
import es.upm.etsisi.poo.controller.CallController;
import es.upm.etsisi.poo.controller.UserController;
import es.upm.etsisi.poo.ia.DummyIA;
import es.upm.etsisi.poo.ia.IAEngine;

public class App {

    public static void main(String[] args ) {

        IAEngine iaEngine = new DummyIA();

        UserController userController = new UserController();
        CallController callController = new CallController(userController, iaEngine);
        AgentController agentController = new AgentController(userController, iaEngine);
        BillController billController = new BillController(userController);

        CommandParser parser = new CommandParser(userController, callController, agentController, billController);

        CLI cli = new CLI(parser);

        if(args.length > 0){
            cli.runFromFile(args[0]);
        } else {
            cli.runInteractive();
        }
    }
}
