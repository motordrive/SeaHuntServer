package seahunt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 1. Initializer needs to modify calendar
 * 2. need to set herokuapp to localhost in xcode
 * 3. verify against http://www.holynames-sea.org/Calendar/#/?i=22
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
