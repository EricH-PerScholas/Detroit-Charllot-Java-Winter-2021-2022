package perscholas.dependencyinjectionexample;

import org.springframework.stereotype.Component;

@Component
public class Worker1 {

    public void doWork() {
        System.out.println("worker 1 doing work");
    }
}
