package es.upm.etsisi.poo;

import java.util.ArrayList;
import java.util.List;

public class Bill {
    private final List<Billable> billables;
    private final BillType billType;
    private final double total;

    public Bill(BillType billType, List<Billable> billables) {
        this.billType = billType;
        this.billables = new ArrayList<>(billables);
        this.total = billables.stream()
                .mapToDouble(Billable::getCost)
                .sum();
        markAllAsBilled(billables);
    }

    private void markAllAsBilled(List<Billable> items){
        for(Billable item:items){
            item.markAsBilled();
        }
    }
    public List<Billable> getBillables() {
        return billables;
    }

    public BillType getBillType() {
        return billType;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return String.format("Factura [tipo=%s, elementos=%d, total=%.2f€]",
                billType, billables.size(), total);
    }

}
