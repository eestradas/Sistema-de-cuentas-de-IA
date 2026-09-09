package es.upm.etsisi.poo.command;

import es.upm.etsisi.poo.BillType;
import es.upm.etsisi.poo.controller.BillController;

public class CreateBillCommand implements Command{
    private final BillController billController;
    private final String dni;
    private final BillType billType;

    public CreateBillCommand(BillController billController, String dni, BillType billType) {
        this.billController = billController;
        this.dni = dni;
        this.billType = billType;
    }

    @Override
    public void execute() {
        billController.createBill(dni, billType);
    }
}
