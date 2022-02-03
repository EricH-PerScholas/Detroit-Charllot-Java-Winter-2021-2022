package perscholas.dependencyinjectionexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Worker1 {

    public static final Logger LOG = LoggerFactory.getLogger(Worker1.class);

    public void doWork() {
        LOG.warn("This is worker 1");

        try {
            int x = 1/ 0;
        } catch ( Exception e ) {
            //LOG.error("This is the message" , e);
        }

    }
}
