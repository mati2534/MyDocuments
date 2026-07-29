package pl.Documents.MyDocuments.service;

import pl.Documents.MyDocuments.model.Document;
import pl.Documents.MyDocuments.model.Type;

import java.util.ArrayList;
import java.util.List;

public class MySearchEngine implements SearchEngine {

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


        Type webType = new Type();
        webType.setName("WEB");
        webType.setDesc("Łącze sieciowe");
        webType.setExtension(".url");

        Document webDocument = new Document();
        webDocument.setName("Strona uczelni");
        webDocument.setType(webType);
        webDocument.setLocation("https://example.com");
        result.add(webDocument);


        Type imageType = new Type();
        imageType.setName("IMAGE");
        imageType.setDesc("Obraz JPEG");
        imageType.setExtension(".jpg");

        Document imageDocument = new Document();
        imageDocument.setName("Zdjęcie wakacyjme");
        imageDocument.setType(imageType);
        imageDocument.setLocation("/Documents/Wakacje2026.jpg");
        result.add(imageDocument);


        Type wordType = new Type();
        wordType.setName("WORD");
        wordType.setDesc("Dokument Microsoft Word");
        wordType.setExtension(".docx");

        Document wordDocument = new Document();
        wordDocument.setName("Notatki z zajęć");
        wordDocument.setType(wordType);
        wordDocument.setLocation("/Documents/Notatki.docx");
        result.add(wordDocument);


        return result;
    }

}
