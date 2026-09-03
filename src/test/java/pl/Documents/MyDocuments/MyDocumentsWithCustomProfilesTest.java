package pl.Documents.MyDocuments;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
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
@ContextConfiguration("classpath:mydocuments-custom-profiles-context.xml")
//@ProfileValueSourceConfiguration(CustomeProfile.class) - JUnit4
public class MyDocumentsWithCustomProfilesTest {
    private static final Logger log = LoggerFactory.getLogger(MyDocumentsWithCustomProfilesTest.class);

    @Autowired
    private SearchEngine engine;
    @Autowired
    private Type webType;

    //@IfProfileValue(name = "environment", value = "dev") - JUnit4
    @EnabledIfSystemProperty(named = "environment", matches = "dev")
    @Test
    public void testUsingSpringTestWithCustomProfilesX(){
        try {
            log.debug("Użycie narzędzi Spring Test: ");

            List<Document> documents = engine.findByType(webType);
            assertNotNull(documents);
            assertTrue(documents.size() == 1);
            assertEquals(webType.getName(),documents.get(0).getType().getName());
            assertEquals(webType.getDesc(),documents.get(0).getType().getDesc());
            assertEquals(webType.getExtension(),documents.get(0).getType().getExtension());

            documents = engine.listAll();
            assertNotNull(documents);
            assertTrue(documents.size() == 4);
        }catch (Exception ex){
            log.error(ex.getMessage());
        }
    }

    //@IfProfileValue(name ="os.name", value = "Unix") - JUnit4
    @EnabledIfSystemProperty(named="os.name", matches = "Unix")
    @Test
    public void testUsingSpringTestWithCustomProfilesY(){
        try{
            log.debug("Użycie narzędzi Spring Test w systemie Unix: ");




        }catch (Exception ex){
            log.error(ex.getMessage());
        }
    }

}
