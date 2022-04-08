package pl.bie.service.impl;

import pl.bie.entity.RecordEntity;
import pl.bie.executor.HibernateExecutor;
import pl.bie.executor.ORMExecutor;
import pl.bie.model.Voivodeship;
import pl.bie.service.VoivodeshipService;

import java.util.ArrayList;
import java.util.List;

public class VoivodeshipServiceHibernateImpl implements VoivodeshipService {

    private final HibernateExecutor hibernateExecutor= new HibernateExecutor();

    private final List<Voivodeship> voivodeshipList = new ArrayList<>();

    @Override
    public List<Voivodeship> read() {
        hibernateExecutor.bootstrap();
        hibernateExecutor.read();
        return null;

    }

    @Override
    public void printAll() {
        hibernateExecutor.bootstrap();
        hibernateExecutor.print();
    }



    @Override
    public void save() {
        hibernateExecutor.bootstrap();
        hibernateExecutor.save();
    }


}
