package pl.bie.executor;

import pl.bie.entity.RecordEntity;

import java.util.List;

public interface ORMExecutor {

    void bootstrap();
    void save();
    List<RecordEntity> read();
    void print();

}
