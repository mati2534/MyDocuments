package pl.Documents.MyDocuments.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.Documents.MyDocuments.model.Document;
import pl.Documents.MyDocuments.model.Type;

import java.util.List;

//oznacza klasę jako komponent zarządzany przez kontener IoC (Inversion of Control).
@Component
@Profile("qa")
public class FileSearchEngineService implements SearchEngine{

    public List<Document> findByType(Type documentType){
        //natychmiast przerywa działanie metody i rzuca standardowy wyjątek informujący, że dana operacja lub metoda nie jest wspierana w tym miejscu.
        throw new UnsupportedOperationException("Środowisko QA. Operacja jeszcze niezaimplementowana.");
    }

    public List<Document> listAll(){
        throw new UnsupportedOperationException("Środowisko QA. Operacja jeszcze niezaimplementowana.");
    }
}
