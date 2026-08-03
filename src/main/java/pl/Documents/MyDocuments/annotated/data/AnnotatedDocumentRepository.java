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

        //tworzy nową tablicę typu Document[] o rozmiarze równym liczbie elementów w liście result, metoda toArray() wypełnia tę tablicę dokumentami z listy result i zwraca ją jako Document[]
        return result.toArray(new Document[result.size()]);
    }

}
