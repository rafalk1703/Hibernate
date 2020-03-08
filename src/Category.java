import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int categoryID;

    private String name;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
        products = new LinkedList<>();
    }

    public void addProduct(Product product) {
        this.products.add(product);
        product.setCategory(this);
    }

    public void deleteProduct(Product product){
        if(this.products.contains(product))
            this.products.remove(product);
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}