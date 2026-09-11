package pl.Documents.MyDocuments;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.Documents.MyDocuments.model.Document;
import pl.Documents.MyDocuments.model.Type;
import pl.Documents.MyDocuments.service.SearchEngine;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:mydocuments-aop-context.xml")
public class MyDocumentsAOPTest {
    private static final Logger log = LoggerFactory.getLogger(MyDocumentsAOPTest.class);

    @Autowired
    private SearchEngine engineProxy;
    @Autowired
    private Type webType;

    @Test
    public void testUsingSpringAOP(){
        log.debug("Użycie technik AOP systemu Spring: ");

        List<Document> documents = engineProxy.findByType(webType);
        assertNotNull(documents);
        assertTrue(documents.size() == 1);
        assertEquals(webType.getName(), documents.get(0).getType().getName());
        assertEquals(webType.getDesc(), documents.get(0).getType().getDesc());
        assertEquals(webType.getExtension(), documents.get(0).getType().getExtension());
        documents = engineProxy.listAll();
        assertNotNull(documents);
        assertTrue(documents.size() == 4);
        try{
            engineProxy.findByLocation("ścieżka/");
        }catch(Exception ex){
            log.error(ex.getMessage(),ex);
        }
    }

}
