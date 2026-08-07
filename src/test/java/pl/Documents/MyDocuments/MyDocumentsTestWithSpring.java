package pl.Documents.MyDocuments;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import pl.Documents.MyDocuments.model.Document;
import pl.Documents.MyDocuments.model.Type;
import pl.Documents.MyDocuments.service.SearchEngine;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
//wymuszenie uzycia konfiguracji xml w application.properies
@ActiveProfiles("xml")
public class MyDocumentsTestWithSpring {

    private ClassPathXmlApplicationContext context;
    private SearchEngine engine;
    private Type documentType;

    //@Before — adnotacja JUnit 4.Metoda setup() uruchamia się przed każdą metodą testową. Dzięki temu każdy test zaczyna od świeżo przygotowanych obiektów.
    @BeforeEach
    public void setup() {
        //Tworzy kontekst Springa i ładuje definicje beanów z pliku mydocuments-context.xml
        context = new ClassPathXmlApplicationContext("mydocuments-context.xml");

        //Pobiera z kontekstu bean implementujący interfejs SearchEngine z MySearchEngine.
        engine = context.getBean(SearchEngine.class);
        documentType = context.getBean(Type.class);
    }

    @Test
    public void testWithSpringFindByType(){
        List<Document> documents = engine.findByType(documentType);
        assertNotNull(documents);
        assertTrue(documents.size() == 1);
        // porównanie name z mydocuments-context a MySeqarchEngine.
        assertEquals(documentType.getName(), documents.get(0).getType().getName());
        assertEquals(documentType.getDesc(), documents.get(0).getType().getDesc());
        assertEquals(documentType.getExtension(), documents.get(0).getType().getExtension());
    }

    @Test
    public void testWithSpringFindAll(){
        List<Document> documents = engine.listAll();
        assertNotNull(documents);
        assertTrue(documents.size() == 4);
    }
}
