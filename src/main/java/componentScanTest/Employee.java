package componentScanTest;

import org.springframework.stereotype.Component;

@Component
public class Employee {

    public void getEmployee() {
        System.out.println("This is employee class");
    }
}
