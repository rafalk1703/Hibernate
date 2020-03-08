import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Company {

    @Id
    protected String companyName;

    @Embedded
    private Address address;

    public Company() {
    }

    public Company(String name) {
        this.companyName = name;
    }
}