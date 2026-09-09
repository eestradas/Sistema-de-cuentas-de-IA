package es.upm.etsisi.poo.command;

import es.upm.etsisi.poo.controller.AgentController;

public class AddAgentCommand implements Command{

    private final AgentController agentController;
    private final String dni;
    private final int agentId;

    public AddAgentCommand(AgentController agentController, String dni, int agentId) {
        this.agentController = agentController;
        this.dni = dni;
        this.agentId = agentId;
    }

    @Override
    public void execute() {
        agentController.addAgent(dni, agentId);
    }
}
