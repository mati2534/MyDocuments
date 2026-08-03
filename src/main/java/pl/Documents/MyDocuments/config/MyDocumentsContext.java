package pl.Documents.MyDocuments.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.Documents.MyDocuments.data.DocumentDAO;
import pl.Documents.MyDocuments.data.DocumentRepository;
import pl.Documents.MyDocuments.model.Document;
import pl.Documents.MyDocuments.model.Type;
import pl.Documents.MyDocuments.service.SearchEngine;
import pl.Documents.MyDocuments.service.SearchEngineService;

import java.util.HashMap;
import java.util.Map;

//Spring odczytuje klase, a metody oznaczone @Bean traktuje jako instrukcje tworzenia obiektów zarządzanych przez Springa.
@Configuration
public class MyDocumentsContext {

    // Map - magazyn obiektów.
    private Map<String, Document> documents = new HashMap<String, Document>();
    private Map<String, Type> types = new HashMap<String,Type>();

    //@Bean oznacza, że Spring zarejestruje zwrócony obiekt jako bean.
    @Bean
    //Metoda pobiera z mapy types typ o kluczu "web" — czyli obiekt typu Type z nazwą WEB.
    public  Type webType(){
        return getTypeFromMap("web");
    }

    @Bean
    public SearchEngine engine(){
        SearchEngineService engine = new SearchEngineService();
        engine.setDocumentDAO(documentDAO());
        return engine;
    }

    //Konstruktor uruchamia się przy tworzeniu konfiguracji i ręcznie przygotowuje dane.
    public MyDocumentsContext(){
        Type type =  new Type();
        type.setName("PDF");
        type.setDesc("Portable Document Format");
        type.setExtension(".pdf");

        Document document = new Document();
        document.setName("Szablon książki");
        document.setType(type);
        document.setLocation("/Documents/Book Template.pdf");

            documents.put("doc1", document);
            types.put("pdf", type);


        document = new Document();
        document.setName("Przykładowa umowa");
        document.setType(type);
        document.setLocation("/Users/felipeg/Documents/Contracts/Przykladowa umowa.pdf");

            documents.put("doc2",document);


        type = new Type();
        type.setName("NOTE");
        type.setDesc("Notatki tekstowe");
        type.setExtension(".txt");

        document = new Document();
        document.setName("Clustering with RabbitMQ");
        document.setType(type);
        document.setLocation("/Users/felipeg/Documents/Random/Clustering with RabbitMQ.txt");

            documents.put("doc3",document);
            types.put("note",type);

        type = new Type();
        type.setName("WEB");
        type.setDesc("Łącze sieciowe");
        type.setExtension(".url");

        document = new Document();
        document.setName("Pro Spring Security Book");
        document.setType(type);
        document.setLocation("http://www.apress.com/9874456789");

            documents.put("doc4",document);
            types.put("web",type);
    }

    //Tworzy DocumentRepository, pobiera dokumenty z mapy i przekazuje je do pól doc1–doc4. Ten obiekt jest potem przekazywany do engine.
    private DocumentDAO documentDAO(){
        DocumentRepository documentDAO = new DocumentRepository();
        documentDAO.setDoc1(getDocumentFromMap("doc1"));
        documentDAO.setDoc2(getDocumentFromMap("doc2"));
        documentDAO.setDoc3(getDocumentFromMap("doc3"));
        documentDAO.setDoc4(getDocumentFromMap("doc4"));
        return documentDAO;
    }

    private Document getDocumentFromMap(String documentKey){
        return documents.get(documentKey);
    }

    private Type getTypeFromMap(String typeKey){
        return types.get(typeKey);
    }

    //konstruktor
    //  → tworzy doc1–doc4 i typy
    //  → zapisuje je w Mapach
    //  → documentDAO() pobiera dokumenty z mapy
    //  → engine() przekazuje DocumentDAO do SearchEngineService
    //  → Spring udostępnia bean engine
}
