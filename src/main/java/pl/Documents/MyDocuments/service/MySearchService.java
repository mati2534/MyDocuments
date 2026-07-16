package pl.Documents.MyDocuments.service;

import pl.Documents.MyDocuments.model.Document;
import pl.Documents.MyDocuments.model.Type;

import java.util.ArrayList;
import java.util.List;

public class MySearchService implements SearchEngine {

    @Override
    //przeszukuje listę dokumentów i zwraca tylko te, które mają określony typ. implementuje findByType z SearchEngine
    public List<Document> findByType(Type documentType) {
        // tworzy liste result
        List<Document> result = new ArrayList<Document>();
        //Przechodzi po wszystkich dokumentach zwróconych przez metodę storage()
        for (Document document : storage()) {
            //porównanie typów
            if (document.getType().getName().equals(documentType.getName()))
                result.add(document);
        }
        return result;
    }

    @Override
    public List<Document> listAll(){
        return storage();
    }

    private List<Document> storage(){
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

        return result;
    }

}
