package pl.Documents.MyDocuments;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pl.Documents.MyDocuments.views.ResourceLoaderMenu;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MyDocumentsWithResourceLoaderInjectionTest {
    private static final Logger log = LoggerFactory.getLogger(MyDocumentsWithResourceLoaderInjectionTest.class);
    private ClassPathXmlApplicationContext context;

    @BeforeEach
    public void setup(){
        context = new ClassPathXmlApplicationContext("mydocuments-resourceloader-injection-context.xml");
    }

    @Test
    public void testMenu(){
        log.debug("Wywołanie menu jako wstrzyknięcia Resourceloader: ");
        ResourceLoaderMenu menu = context.getBean(ResourceLoaderMenu.class);
        assertNotNull(menu);
        menu.printMenu("classpath:data/menu.txt");
    }
}
