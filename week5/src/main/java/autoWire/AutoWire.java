package autoWire;

import org.springframework.stereotype.Component;


@Component
public class AutoWire {
    public AutoWire() {
        System.out.println("Construct Example");
    }

    public void info() {
        System.out.println("Auto wiring example");
    }
}
