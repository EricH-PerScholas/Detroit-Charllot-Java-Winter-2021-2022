package perscholas.dependencyinjectionexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Worker3 {

    public static final Logger LOG = LoggerFactory.getLogger(Worker3.class);

    public void doWork() {
        LOG.debug("This is worker 3");
    }
}
