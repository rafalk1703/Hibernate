import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Invoice {

    private static final String CUSTOMER_FK = "CUSTOMER_FK";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int invoiceNumber;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = Invoice.CUSTOMER_FK)
    private Customer customer;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Product> products;

    public Invoice() {
    }

    public Invoice(int quantity, Customer customer) {
        this.customer = customer;
        this.quantity = quantity;
        this.products = new HashSet<>();
    }

    public Invoice(Integer quantity, Customer customer, Set<Product> products) {
        this.quantity = quantity;
        this.customer = customer;
        this.products = products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
        product.getInvoices().add(this);
    }

    public Set<Product> getProducts() {
        return products;
    }


    public void setCustomer(Customer customer) {
        this.customer = customer;
        customer.getInvoices().add(this);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}