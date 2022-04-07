package pl.bie.executor;


import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import pl.bie.entity.RecordEntity;
import pl.bie.model.Voivodeship;
import pl.bie.service.VoivodeshipService;
import pl.bie.service.impl.VoivodeshipServiceXMLImpl;

import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class HibernateExecutor implements ORMExecutor {

    private static SessionFactory factory;
    private static VoivodeshipService voivodeshipService;

    @Override
    public void bootstrap() {
        try {
            factory = new Configuration().configure().buildSessionFactory();
            System.out.println("Configuration: " + factory);
            voivodeshipService = new VoivodeshipServiceXMLImpl();

        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    @Override
    public void save() {
        initializeCheck();
        saveIntoDB(voivodeshipService.read());
        shutDown();
    }

    @Override
    public void read() {
        initializeCheck();

    }

    @Override
    public void print() {
        initializeCheck();

    }

    private void initializeCheck() {
        if (factory == null) {
            throw new IllegalStateException(
                    "Session factory is not initialized. You have to call bootstrap() first!");
        }
    }

    private void shutDown() {
        factory.close();


    }

    private void saveIntoDB(List<Voivodeship> data) {
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
                        session.close();
                    }
                }
                System.out.println("Data successfully added!");
            } catch (HibernateException e) {
                if (transaction != null) transaction.rollback();
                e.printStackTrace();
            }

        }
    }
}

