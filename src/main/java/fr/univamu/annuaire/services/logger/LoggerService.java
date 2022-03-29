package fr.univamu.annuaire.services.logger;

public interface LoggerService {
    void info(String msg);
    void info(Exception e);

    void warning(String msg);
    void warning(Exception e);

    void error(String msg);
    void error(Exception e);
}
