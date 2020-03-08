import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity

public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String companyName;
    private String street;
    private String city;

    @OneToMany(mappedBy = "supplier")
    private Set<Product> productSet;

    public Supplier() {
    }

    public void addProduct(Product product) {
        productSet.add(product);
    }

    public Supplier(String companyName, String street, String city) {
        this.companyName = companyName;
        this.street = street;
        this.city = city;
        productSet = new HashSet<>();
    }

}
