package pl.bie.service;

import pl.bie.model.Voivodeship;

import java.util.List;

public interface VoivodeshipService {

    List<Voivodeship> read(String path);
    void printData();

}
