package es.upm.etsisi.poo.controller;

import es.upm.etsisi.poo.*;

import java.util.ArrayList;
import java.util.List;

public class BillController {

    private final UserController userController;

    public BillController(UserController userController){
        this.userController = userController;
    }

    public void createBill(String dni, BillType billType){
        PremiumUser user = userController.getPremiumUser(dni);

        if(user == null) {
            if(userController.getUser(dni) == null) {
                System.out.println(Messages.userNotFound(dni));
            } else {
                System.out.println(Messages.NOT_PREMIUM);
            }
            return;
        }

        List<Billable> items = collectPendingItems(user, billType);

        if(items.isEmpty()){
            System.out.println(Messages.NO_PENDING_ITEMS);
            return;
        }

        Bill bill = new Bill(billType, items);
        user.addBill(bill);
        System.out.println(Messages.billCreated(billType.name(), bill.getTotal()));
    }


    private List<Billable> collectPendingItems(PremiumUser user, BillType billType){
        List<Billable> items = new ArrayList<>();

        if(billType == BillType.CALL || billType == BillType.MIXED){
            for(DirectCall call : user.getPendingCalls()){
                if(!call.isBilled()){
                    items.add(call);
                }
            }
        }

        if(billType == BillType.AGENT || billType == BillType.MIXED){
            for(AgentExecution execution : user.getPendingExecutions()){
                if(!execution.isBilled()){
                    items.add(execution);
                }
            }
        }

        return items;
    }
}
