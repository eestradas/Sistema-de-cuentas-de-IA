package es.upm.etsisi.poo;

import java.util.ArrayList;
import java.util.List;

public class PremiumUser extends User{

    private static final double COST_PER_WORD = 0.01;

    private final String accountNumber;

    private final List<DirectCall> pendingCalls;
    private final List<AgentExecution> pendingExecutions;
    private final List<Bill> bills;

    public PremiumUser(String dni, String name, String password, String accountNumber) {
        super(dni, name, password);
        this.accountNumber = accountNumber;
        this.pendingCalls = new ArrayList<>();
        this.pendingExecutions = new ArrayList<>();
        this.bills = new ArrayList<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }



    @Override
    public boolean canExecute(int wordCount) {
        return true;
    }

    @Override
    public double registerUsage(int wordCount) {
        int free = getFreeWordsRemaining();
        if(wordCount <= free) {
            consumeFreeWords(wordCount);
            return 0.0;
        }
        consumeFreeWords(free);
        int paidWords = wordCount - free;
        return paidWords * COST_PER_WORD;
    }



    public void addPendingCall(DirectCall call){
        pendingCalls.add(call);
    }

    public void addPendingExecution(AgentExecution execution){
        pendingExecutions.add(execution);
    }

    public List<DirectCall> getPendingCalls(){
        return pendingCalls;
    }

    public List<AgentExecution> getPendingExecutions(){
        return pendingExecutions;
    }



    public List<Bill> getBills() {
        return bills;
    }

    public void addBill(Bill bill) {
        bills.add(bill);
    }
}
