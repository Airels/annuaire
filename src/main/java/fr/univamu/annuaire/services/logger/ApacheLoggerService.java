package fr.univamu.annuaire.services.logger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("apacheLogger")
public class ApacheLoggerService implements LoggerService {

    private static Log log = LogFactory.getLog(ApacheLoggerService.class);

    @Override
    public void info(String msg) {
        log.info(msg);
    }

    @Override
    public void info(Exception e) {
        log.info("An exception occured: ", e);
    }

    @Override
    public void warning(String msg) {
        log.warn(msg);
    }

    @Override
    public void warning(Exception e) {
        log.warn("An exception occured: ", e);
    }

    @Override
    public void error(String msg) {
        log.error(msg);
    }

    @Override
    public void error(Exception e) {
        log.error("An exception occured: ", e);
    }
}
