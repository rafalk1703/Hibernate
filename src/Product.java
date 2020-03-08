import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Product {
    private static final String SUPPLIER_FK = "SUPPLIER_FK";
    private static final String CATEGORY_FK= "CATEGORY_FK";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String productName;

    private int unitsInStock;

    @ManyToOne
    @JoinColumn(name = SUPPLIER_FK)
    private Supplier supplier;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = CATEGORY_FK)
    private Category category;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Invoice> invoices;

    public Product() {
    }

    public Product(String name, int unitsInStock) {
        this.productName = name;
        this.unitsInStock = unitsInStock;
        invoices = new HashSet<>();
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
        supplier.addProduct(this);
    }

    public void setCategory(Category category) {
        this.category = category;
        category.getProducts().add(this);
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public Category getCategory() {
        return category;
    }

    public Set<Invoice> getInvoices() {
        return invoices;
    }

    public void addInvoice(Invoice invoice) {
        this.invoices.add(invoice);
        invoice.getProducts().add(this);
    }

    @Override
    public String toString() {
        return "Product{ " +
                "Name: " + productName + '\n' +
                ", UnitsInStock=" + unitsInStock + '\n' +
                ", Supplier=" + supplier + '\n' +
                ", Category=" + category + '}';
    }
}