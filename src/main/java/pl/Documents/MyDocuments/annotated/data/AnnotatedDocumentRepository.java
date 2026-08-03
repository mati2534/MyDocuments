package pl.Documents.MyDocuments.annotated.data;

import org.springframework.stereotype.Repository;
import pl.Documents.MyDocuments.data.DocumentDAO;
import pl.Documents.MyDocuments.model.Document;
import pl.Documents.MyDocuments.model.Type;

import java.util.ArrayList;
import java.util.List;

@Repository("documentDAO")
public class AnnotatedDocumentRepository implements DocumentDAO {

    //przekazuje całą tę tablicę do AnnotatedSearchEngine
    public Document[] getAll(){
        return storage();
    }

    private Document[] storage(){
         List<Document> result = new ArrayList<Document>();

        Type type =  new Type();
        type.setName("PDF");
        type.setDesc("Portable Document Format");
        type.setExtension(".pdf");

        Document document = new Document();
        document.setName("Szablon książki");
        document.setType(type);
        document.setLocation("/Documents/Book Template.pdf");

            result.add(document);


        document = new Document();
        document.setName("Przykładowa umowa");
        document.setType(type);
        document.setLocation("/Users/felipeg/Documents/Contracts/Przykladowa umowa.pdf");

            result.add(document);


        type = new Type();
        type.setName("NOTE");
        type.setDesc("Notatki tekstowe");
        type.setExtension(".txt");

        document = new Document();
        document.setName("Clustering with RabbitMQ");
        document.setType(type);
        document.setLocation("/Users/felipeg/Documents/Random/Clustering with RabbitMQ.txt");

            result.add(document);

        type = new Type();
        type.setName("WEB");
        type.setDesc("Łącze sieciowe");
        type.setExtension(".url");

        document = new Document();
        document.setName("Pro Spring Security Book");
        document.setType(type);
        document.setLocation("http://www.apress.com/9874456789");

            result.add(document);

        //tworzy nową tablicę typu Document[] o rozmiarze równym liczbie elementów w liście result, metoda toArray() wypełnia tę tablicę dokumentami z listy result i zwraca ją jako Document[]
        return result.toArray(new Document[result.size()]);
    }

}
