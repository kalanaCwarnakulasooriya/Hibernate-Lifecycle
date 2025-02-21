import config.FactoryConfiguration;
import entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {
    public static void main(String[] args) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

//        ** 01. Transient State **
//        no sessin
//        no db
//        no changes tracking
        Customer customer = new Customer();
        customer.setName("Nimal");
//        ------------------------------------------------------------------------------------------

//        ** 02. Persistent State **
//        yes session
//        new object no db , get() yes db
//        session.save(customer);
//        yes changes track
        session.persist(customer);
        System.out.println("Persisted : " + customer.getId());

        transaction.commit();
//        new object yes db
//        ------------------------------------------------------------------------------------------

//        ** 03. Detached State **
//        no session
//        yes db
//        changes track (Hibernate)
        session.close();
        System.out.println("Detached : " + customer.getId());

//        Modify detached entity(customer object)
        customer.setName("Kamal");

//        get new session
        session = FactoryConfiguration.getInstance().getSession();
        //begin new transaction
        transaction = session.beginTransaction();
//        ------------------------------------------------------------------------------------------

//        ** 04. Reattaching to Persistent State **
//        session.update(customer);
//        session.save(customer);
        Customer mergeCustomer = session.merge(customer);
        transaction.commit();
        System.out.println("Merged : " + mergeCustomer.getName());
        session.close();
//        ------------------------------------------------------------------------------------------

        session = FactoryConfiguration.getInstance().getSession();
        transaction = session.beginTransaction();
//        ** 05. Remove State **
//        yes session
//        db yes but commit no db
//        .delete(mergeCustomer)
        session.remove(mergeCustomer);
        transaction.commit();

        session.close();

//        mergeCustomer eligible to GC
        System.out.println("Removed : " + mergeCustomer);
    }
}
