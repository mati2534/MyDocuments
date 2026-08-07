package pl.Documents.MyDocuments;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import pl.Documents.MyDocuments.model.Document;
import pl.Documents.MyDocuments.model.Type;
import pl.Documents.MyDocuments.service.SearchEngine;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
//wymuszenie uzycia konfiguracji xml w application.properies
@ActiveProfiles("xml")
public class MyDocumentsTest {
    private ClassPathXmlApplicationContext context;
    private SearchEngine engine;
    private Type webType;

    @BeforeEach
    public void setUp() {
        context = new ClassPathXmlApplicationContext("mydocuments-context.xml");

    }

    @Test
    public void testAll() {
        engine = context.getBean(SearchEngine.class);
        webType = context.getBean("webType", Type.class);

        List<Document> documents = engine.findByType(webType);
        assertNotNull(documents);
        assertTrue(documents.size() == 1);
        //porównanie w mydocuments-context doc4 z webType
        assertEquals(webType.getName(), documents.get(0).getType().getName());
        assertEquals(webType.getDesc(), documents.get(0).getType().getDesc());
        assertEquals(webType.getExtension(), documents.get(0).getType().getExtension());

        engine = context.getBean(SearchEngine.class);

        documents = engine.listAll();
        assertNotNull(documents);
        assertTrue(documents.size() == 4);
    }


}
