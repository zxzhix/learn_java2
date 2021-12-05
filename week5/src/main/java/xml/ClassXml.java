package xml;

import org.springframework.stereotype.Component;


/**
 * xml配置bean
 */
@Component
public class ClassXml {
    public ClassXml() {
        System.out.println("Construct Example");
    }

    public void info() {
        System.out.println("Auto wiring example");
    }
}
