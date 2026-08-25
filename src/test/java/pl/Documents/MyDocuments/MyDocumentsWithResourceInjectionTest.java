package pl.Documents.MyDocuments;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pl.Documents.MyDocuments.views.Menu;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class MyDocumentsWithResourceInjectionTest {
    private static final Logger log = LoggerFactory.getLogger(MyDocumentsWithResourceInjectionTest.class);
    private ClassPathXmlApplicationContext context;

    @BeforeEach
    public void setup() {
        context = new ClassPathXmlApplicationContext("mydocuments-resource-injection-context.xml");
    }
    @Test
    public void testMenu() {
        log.debug("Wywołanie menu jako wstrzyknięcia zasobu: ");
        Menu menu = context.getBean(Menu.class);
        assertNotNull(menu);
        menu.printMenu();
    }
}
