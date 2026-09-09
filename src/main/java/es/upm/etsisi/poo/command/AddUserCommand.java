package es.upm.etsisi.poo.command;

import es.upm.etsisi.poo.controller.UserController;

public class AddUserCommand implements Command{

    private final UserController userController;
    private final String dni;
    private final String name;
    private final String password;
    private final String accountNumber;

    public AddUserCommand(UserController userController, String dni, String name, String password, String numeroCuenta) {
        this.userController = userController;
        this.dni = dni;
        this.name = name;
        this.password = password;
        this.accountNumber = numeroCuenta;
    }

    @Override
    public void execute() {
        if(accountNumber != null) {
            userController.addPremiumUser(dni, name, password, accountNumber);
        } else {
            userController.addNormalUser(dni, name, password);
        }
    }
}
