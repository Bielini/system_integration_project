package pl.bie.service;

import pl.bie.entity.RecordEntity;
import pl.bie.model.Voivodeship;

import java.util.List;

public interface VoivodeshipService {

    List<Voivodeship> read();

    void printAll();

    void save();

}
