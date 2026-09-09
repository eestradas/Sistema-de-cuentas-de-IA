package es.upm.etsisi.poo.controller;

import es.upm.etsisi.poo.*;
import es.upm.etsisi.poo.ia.IAEngine;

public class CallController {

    private final UserController userController;
    private final IAEngine iaEngine;

    public CallController(UserController userController, IAEngine iaEngine) {
        this.userController = userController;
        this.iaEngine = iaEngine;
    }

    public void sendCall(String dni, String text){
        User user = userController.getUser(dni);

        if (user == null) {
            System.out.println(Messages.userNotFound(dni));
            return;
        }

        int wordCount = Utilities.countWords(text);

        if(!user.canExecute(wordCount)){
            System.out.println(Messages.wordLimitExceeded(wordCount, user.getFreeWordsRemaining()));
            return;
        }

        String response = iaEngine.query(text);
        double cost = user.registerUsage(wordCount);

        if(cost > 0 && user instanceof PremiumUser){
            DirectCall call = new DirectCall(text, cost);
            ((PremiumUser) user).addPendingCall(call);
            System.out.println(Messages.callResponseWithCost(response, cost));
        } else {
            System.out.println(Messages.callResponse(response));
        }
    }
}

