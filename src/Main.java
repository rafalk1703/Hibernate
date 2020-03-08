import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


public class Main {
    private static SessionFactory sessionFactory = null;

    public static void main(String[] args) {



        sessionFactory = getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        tx.commit();
        showMainMenu(session);
        session.close();
        sessionFactory.close();
    }

    private static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            sessionFactory = configuration.configure().buildSessionFactory();
        }
        return sessionFactory;
    }

    private static void showMainMenu(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What do you want to do?");
        System.out.println("P - show products");
        System.out.println("C - show customers");
        System.out.println("S - show suppliers");
        System.out.println("O - make order");
        String option = scanner.nextLine();
        Transaction tx = session.beginTransaction();
        switch (option) {
            case "P","p" -> {
                TypedQuery<Product> productsQuery = session.createQuery("from Product", Product.class);
                List<Product> products = productsQuery.getResultList();
                for (var p : products)
                    System.out.println(p.toString());
            }
            case "C", "c" -> {
                TypedQuery<Customer> customersQuery = session.createQuery("from Customer", Customer.class);
                List<Customer> customers = customersQuery.getResultList();
                for (var c : customers)
                    System.out.println(c.toString());
            }
            case "S", "s" -> {
                TypedQuery<Supplier> suppliersQuery = session.createQuery("from Supplier", Supplier.class);
                List<Supplier> suppliers = suppliersQuery.getResultList();
                for (var s : suppliers)
                    System.out.println(s.toString());
            }
            case "O", "o" -> {
                Set<Product> newProducts = new HashSet<>();
                System.out.print("Your company name: ");
                String companyName = scanner.nextLine();
                Customer customer = session.get(Customer.class, companyName);
                if (customer == null) {
                    System.out.println("Wrong company name");
                    break;
                }
                System.out.print("Products' names (empty line to end): ");
                String productName = scanner.nextLine();
                while (!productName.equals("")) {
                    productName = scanner.nextLine();
                    Product prod = session.get(Product.class, productName);
                    if (prod != null) {
                        newProducts.add(prod);
                    } else {
                        System.out.println("Wrong product name. It won't be added to order");
                        break;
                    }
                }
                System.out.print("Number of units: ");
                int number = scanner.nextInt();
                Invoice invoice = new Invoice(number, customer, newProducts);
                session.save(invoice);
            }
            default -> System.out.println("Invalid option");
        }
        tx.commit();
    }
}
