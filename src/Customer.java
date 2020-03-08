import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Customer extends Company {

    private double discount;

    @OneToMany(mappedBy = "customer")
    private Set<Invoice> invoices;


    public Customer() {
    }

    public Customer(String name) {
        super(name);
        invoices = new HashSet<>();
    }

    public void addInvoice(Invoice i) {
        this.invoices.add(i);
        i.setCustomer(this);
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Set<Invoice> getInvoices() {
        return this.invoices;
    }
}