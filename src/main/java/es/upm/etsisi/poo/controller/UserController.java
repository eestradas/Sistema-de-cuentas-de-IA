package es.upm.etsisi.poo.controller;

import es.upm.etsisi.poo.*;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserController {

    private final Map<String, User> users = new HashMap<>();

    public void addNormalUser(String dni, String name, String password){
        if(!Utilities.isValidDni(dni)){
            System.out.println(Messages.invalidDni(dni));
            return;
        }
        if(users.containsKey(dni)){
            System.out.println(Messages.userAlreadyExists(dni));
            return;
        }
        users.put(dni, new NormalUser(dni, name, password));
        System.out.println(Messages.userCreated(name,dni));
    }

    public void addPremiumUser(String dni, String name, String password, String accountNumber){
        if (!Utilities.isValidDni(dni)) {
            System.out.println(Messages.invalidDni(dni));
            return;
        }
        if (users.containsKey(dni)) {
            System.out.println(Messages.userAlreadyExists(dni));
            return;
        }
        if (!Utilities.isValidAccount(accountNumber)) {
            System.out.println(Messages.invalidAccount());
            return;
        }
        users.put(dni, new PremiumUser(dni, name, password, accountNumber));
        System.out.println(Messages.userCreated(name,dni));
    }



    public User getUser(String dni){
        return users.get(dni);
    }

    public PremiumUser getPremiumUser(String dni){
        User user = getUser(dni);
        if(user instanceof PremiumUser){
            return (PremiumUser) user;
        }
        return null;
    }

    public Collection<User> getAllUsers(){
        return users.values();
    }
}
