package pl.bie.executor;

public interface ORMExecutor {

    void bootstrap();
    void execute();
    void shutDown();
}
