package perscholas.dependencyinjectionexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Worker2 {

    public static final Logger LOG = LoggerFactory.getLogger(Worker2.class);

    public void doWork() {
        LOG.info("this is worker 2");
    }

}
