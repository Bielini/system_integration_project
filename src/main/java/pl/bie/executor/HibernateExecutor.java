package pl.bie.executor;


import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



public class HibernateExecutor implements ORMExecutor{

    private static SessionFactory factory;

    @Override
    public void bootstrap() {
        try {
            factory = new Configuration().configure().buildSessionFactory();
            System.out.println("Configuration: "+ factory);
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    @Override
    public void execute() {

    }

    @Override
    public void shutDown() {
        factory.close();
    }
}
