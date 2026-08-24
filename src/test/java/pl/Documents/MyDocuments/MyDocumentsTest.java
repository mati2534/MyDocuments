package pl.Documents.MyDocuments;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ActiveProfiles;
import pl.Documents.MyDocuments.model.Document;
import pl.Documents.MyDocuments.model.Type;
import pl.Documents.MyDocuments.service.SearchEngine;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class MyDocumentsTest {
   private static final Logger log = LoggerFactory.getLogger(MyDocumentsTest.class);
   private ClassPathXmlApplicationContext context;


    @BeforeEach
    public void setUp() {
        context = new ClassPathXmlApplicationContext("mydocuments-context.xml");
    }

    @Test
    public void testmenu() {
        log.debug("Wczytanie pliku zasobów: menu.txt");
        //resource służy do lokalizowania pliku menu.txt z katalogu zasobów aplikacji (tzw. classpath).
        Resource resource = context.getResource("classpath:data/menu.txt");
        try{
            //otwiera strumień wejściowy, który pozwala odczytać faktyczną zawartość pliku menu.txt
            InputStream stream = resource.getInputStream();
            //Scanner odczytuje tekst ze strumienia.
            Scanner scanner = new Scanner(stream);
            while(scanner.hasNext())
            {
                System.out.println(scanner.nextLine());
            }
            scanner.close();
            stream.close();
        }catch (IOException e){
            //obsługa błędu.
            e.printStackTrace();
        }





//        engine = context.getBean(SearchEngine.class);
//        webType = context.getBean("webType", Type.class);
//
//        List<Document> documents = engine.findByType(webType);
//        assertNotNull(documents);
//        assertTrue(documents.size() == 1);
//        //porównanie w mydocuments-context doc4 z webType
//        assertEquals(webType.getName(), documents.get(0).getType().getName());
//        assertEquals(webType.getDesc(), documents.get(0).getType().getDesc());
//        assertEquals(webType.getExtension(), documents.get(0).getType().getExtension());
//
//        engine = context.getBean(SearchEngine.class);
//
//        documents = engine.listAll();
//        assertNotNull(documents);
//        assertTrue(documents.size() == 4);
    }


}
