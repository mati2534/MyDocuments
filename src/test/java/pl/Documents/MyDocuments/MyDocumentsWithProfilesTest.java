package pl.Documents.MyDocuments;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.Documents.MyDocuments.model.Document;
import pl.Documents.MyDocuments.model.Type;
import pl.Documents.MyDocuments.service.SearchEngine;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//instruuje framework JUnit 5, aby uruchomił testy przy użyciu mechanizmów Spring TestContext Framework.
@ExtendWith(SpringExtension.class)
//wskazuje Springowi, skąd ma pobrać konfigurację XML.
@ContextConfiguration("classpath:mydocuments-profiles-context.xml")
@ActiveProfiles("dev")
public class MyDocumentsWithProfilesTest {
    private static final Logger log =  LoggerFactory.getLogger(MyDocumentsWithProfilesTest.class);

    @Autowired
    private SearchEngine engine;
    @Autowired
    private Type webType;

    @Test
    public void testUsingSpringTestWithProfiles() {
        try{
            log.debug("Użycie narzędzi Spring Test: ");

            List<Document> documents = engine.findByType(webType);
            assertNotNull(documents);
            assertTrue(documents.size() == 1);
            assertEquals(webType.getName(), documents.get(0).getType().getName());
            assertEquals(webType.getDesc(), documents.get(0).getType().getDesc());
            assertEquals(webType.getExtension(), documents.get(0).getType().getExtension());

            documents = engine.listAll();
            assertNotNull(documents);
            assertTrue(documents.size() == 4);
        }catch (Exception ex){
            log.error(ex.getMessage());
        }
    }
}
