package pl.bie.service.impl;

import pl.bie.executor.HibernateExecutor;
import pl.bie.executor.ORMExecutor;
import pl.bie.model.Voivodeship;
import pl.bie.service.VoivodeshipService;

import java.util.List;

public class VoivodeshipServiceHibernateImpl implements VoivodeshipService {

    private final HibernateExecutor hibernateExecutor= new HibernateExecutor();
    private final VoivodeshipService voivodeshipService = new VoivodeshipServiceXMLImpl();

    @Override
    public List<Voivodeship> read() {
        return null;
    }

    @Override
    public void printData() {

    }

    @Override
    public void save() {
        hibernateExecutor.bootstrap();
        hibernateExecutor.save(voivodeshipService.read());
    }
}
