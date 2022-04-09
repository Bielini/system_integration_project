package pl.bie.api.rest;

import java.net.ConnectException;

public interface RESTInterface {

    void start() throws ConnectException;
    void stop();
}
