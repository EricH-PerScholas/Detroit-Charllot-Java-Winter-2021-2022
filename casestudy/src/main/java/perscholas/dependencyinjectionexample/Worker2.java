package perscholas.dependencyinjectionexample;

import org.springframework.stereotype.Component;

@Component
public class Worker2 {

    public void doWork() {
        System.out.println("worker 2 doing work");
    }

}
