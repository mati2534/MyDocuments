package pl.Documents.MyDocuments;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.Documents.MyDocuments.config.MyDocumentsContext;
import pl.Documents.MyDocuments.model.Document;
import pl.Documents.MyDocuments.model.Type;
import pl.Documents.MyDocuments.service.SearchEngine;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MyDocumentsBeanConfigurationTest {

    private ApplicationContext context;
    private SearchEngine engine;
    private Type webType;

    @BeforeEach
    public void setup(){
        //AnnotationConfigApplicationContext to kontener IoC używany wtedy, gdy konfiguracja Springa jest oparta na adnotacjach i klasach Java, a nie na XML.
        context = new AnnotationConfigApplicationContext(MyDocumentsContext.class);
        engine = context.getBean(SearchEngine.class);
        webType = context.getBean(Type.class);
    }

    @Test
    public void testWithBeanConfigurationFindByType(){
        List<Document> documents = engine.findByType(webType);
        assertNotNull(documents);
        assertTrue(documents.size() == 1);
        assertEquals(webType.getName(),documents.get(0).getType().getName());
        assertEquals(webType.getDesc(),documents.get(0).getType().getDesc());
        assertEquals(webType.getExtension(),documents.get(0).getType().getExtension());
    }
    @Test
    public void testWithBeanConfigurationListAll(){
        List<Document> documents = engine.listAll();
        assertNotNull(documents);
        assertTrue(documents.size() == 4);
    }

}
