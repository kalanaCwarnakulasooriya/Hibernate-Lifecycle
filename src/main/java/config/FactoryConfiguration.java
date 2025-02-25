package config;

import entity.Customer;
//import entity.Customer;
//import entity.IDCard;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {

    private SessionFactory sessionFactory; //session factory ekn session create krl dena object eka.
    private static FactoryConfiguration factoryConfiguration;

    private FactoryConfiguration() {
        Configuration configuration = new Configuration().configure(); //Eliye thiyana xml file eka thniym hoygena read krl link krnne meya.hibernate boot krnw/snap krnw
        configuration.addAnnotatedClass(Customer.class);
//        configuration.addAnnotatedClass(IDCard.class);
        sessionFactory  = configuration.buildSessionFactory();
    }
    public static FactoryConfiguration getInstance() {
        return  (factoryConfiguration == null) ?
                 factoryConfiguration = new FactoryConfiguration():
                 factoryConfiguration;
    }
    public Session getSession() {
        return sessionFactory.openSession();
    }
}
