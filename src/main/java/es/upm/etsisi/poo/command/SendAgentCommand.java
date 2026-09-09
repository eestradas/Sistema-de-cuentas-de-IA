package es.upm.etsisi.poo.command;

import es.upm.etsisi.poo.controller.AgentController;

public class SendAgentCommand implements Command{
    private final AgentController agentController;
    private final String dni;
    private final int agentId;
    private final String input;

    public SendAgentCommand(AgentController agentService, String dni, int agentId, String input) {
        this.agentController = agentService;
        this.dni = dni;
        this.agentId = agentId;
        this.input = input;
    }

    @Override
    public void execute() {
        agentController.sendAgent(dni, agentId, input);
    }
}
