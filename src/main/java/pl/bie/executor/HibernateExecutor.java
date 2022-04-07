package pl.bie.executor;


import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import pl.bie.entity.RecordEntity;
import pl.bie.model.Voivodeship;

import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class HibernateExecutor implements ORMExecutor {

    private static SessionFactory factory;

    @Override
    public void bootstrap() {
        try {
            factory = new Configuration().configure().buildSessionFactory();
            System.out.println("Configuration: " + factory);
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    @Override
    public void execute() {
        if (factory == null) {
            throw new IllegalStateException(
                    "Session factory is not initialized. You have to call bootstrap() first!");
        }

        boolean running = true;
        Scanner scanner = new Scanner(System.in);
        while (running) {
            System.out.println("Hibernate - Menu");
            System.out.println("1 - save");
            System.out.println("2 - read");
            System.out.println("q - exit Hibernate menu");
            String chosenOption = scanner.nextLine();

            switch (chosenOption) {
                case "1":

                    break;
                case "2":
//                    createUser(scanner);
                    break;
                case "q":
                    running = false;
                    break;
                default:
                    System.out.println("Wrong option!");
            }
        }
    }

    @Override
    public void shutDown() {
        factory.close();
    }

    public void save(List<Voivodeship> data) {
        if (data.size() == 0) {
            System.err.println("There are no data, you can take it from xmlFile");
        } else {
            Transaction transaction = null;

            try  {
                Session session = factory.openSession();
                for (Voivodeship datum : data) {
                    for (Map.Entry<String, Double> stringDoubleEntry : datum.getValueByYears().entrySet()) {
                        transaction = session.beginTransaction();
                        RecordEntity recordEntity = new RecordEntity();

                        recordEntity.setCategory(datum.getSourceFile());
                        recordEntity.setVoivodeshipName(datum.getName());
                        recordEntity.setYear(Integer.parseInt(stringDoubleEntry.getKey()));
                        recordEntity.setValue(Float.parseFloat(String.valueOf(stringDoubleEntry.getValue())));
                        session.save(recordEntity);
                        transaction.commit();
                    }
                }



            } catch (HibernateException e) {
                if (transaction != null) transaction.rollback();
                e.printStackTrace();
            }

        }
    }
}

