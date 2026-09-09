package es.upm.etsisi.poo.controller;

import es.upm.etsisi.poo.*;
import es.upm.etsisi.poo.ia.IAEngine;

import java.util.ArrayList;
import java.util.List;

public class AgentController {

    private final UserController userController;
    private final IAEngine iaEngine;

    public AgentController(UserController userController, IAEngine iaEngine){
        this.userController = userController;
        this.iaEngine = iaEngine;
    }

    public void addAgent(String dni, int agentId){
        User user = userController.getUser(dni);
        if (user == null) {
            System.out.println(Messages.userNotFound(dni));
            return;
        }

        if(user.hasAgent(agentId)){
            System.out.println(Messages.agentAlreadyExists(agentId));
            return;
        }
        Agent agent = new Agent(user, agentId);
        user.addAgent(agent);
        System.out.println(Messages.agentCreated(agentId,dni));
    }

    public void sendAgent(String dni, int agentId, String input){
        User user = userController.getUser(dni);
        if (user == null) {
            System.out.println(Messages.userNotFound(dni));
            return;
        }

        if(!user.hasAgent(agentId)){
            System.out.println(Messages.agentNotFound(agentId));
            return;
        }

        String[] tokens = input.split("#");

        int totalWords = 0;
        for(String token : tokens){
            totalWords += Utilities.countWords(token.trim());
        }

        if(!user.canExecute(totalWords)){
            System.out.println(Messages.wordLimitExceeded(totalWords, user.getFreeWordsRemaining()));
            return;
        }

        List<String> responses = new ArrayList<>();
        for(String token: tokens){
            responses.add(iaEngine.query(token.trim()));
        }

        double cost = user.registerUsage(totalWords);

        Agent agent = user.getAgent(agentId);
        String finalResponse = agent.buildResponse(responses, totalWords);

        if(cost > 0 && user instanceof PremiumUser){
            AgentExecution execution = new AgentExecution(input, cost, agentId);
            ((PremiumUser) user).addPendingExecution(execution);
            System.out.println(Messages.agentResponseWithCost(finalResponse, cost));
        } else {
            System.out.println(finalResponse);
        }
    }
}
